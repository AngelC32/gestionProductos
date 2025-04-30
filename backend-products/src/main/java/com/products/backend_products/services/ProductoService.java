package com.products.backend_products.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.products.backend_products.exceptions.DataNotFoundException;
import com.products.backend_products.exceptions.ImagenNotFoundException;
import com.products.backend_products.exceptions.NotImageFormatException;
import com.products.backend_products.models.Categoria;
import com.products.backend_products.models.Producto;
import com.products.backend_products.models.dto.ProductoRequestDTO;
import com.products.backend_products.models.dto.ProductoResponseDTO;
import com.products.backend_products.repository.CategoriaRepository;
import com.products.backend_products.repository.ProductoRepository;
import com.products.backend_products.services.interfaces.IProductoService;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UploadService uploadService;

    @Value("${upload.images.location}")
    private String uploadLocation;

    @Value("${upload.images.handler}")
    private String uploadPathHandler;

    @Value("${url.application}")
    private String urlBase;

    private static final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    @Transactional
    @Override
    public ProductoResponseDTO create(ProductoRequestDTO producto) {
        Categoria categoria=categoriaRepository.findById(producto.getCategoriaId()).orElseThrow(()->new DataNotFoundException("La categoria asociada no existe!"));
        Producto newProducto = new Producto();
        newProducto.setNombre(producto.getNombre());
        newProducto.setPrecio(producto.getPrecio());
        newProducto.setStock(producto.getStock());
        newProducto.setCategoria(categoria);

        return modelMapper.map(productoRepository.save(newProducto), ProductoResponseDTO.class);
    }

    @Transactional
    @Override
    public ProductoResponseDTO update(Long id, ProductoRequestDTO producto) {
        Producto productoDb = productoRepository.findById(id).orElseThrow(()-> new DataNotFoundException("El producto que desea actualizar no existe"));
        
        Categoria categoria=categoriaRepository.findById(producto.getCategoriaId()).orElseThrow(()->new DataNotFoundException("La categoria asociada no existe!"));

        productoDb.setNombre(producto.getNombre());
        productoDb.setPrecio(producto.getPrecio());
        productoDb.setStock(producto.getStock());
        productoDb.setCategoria(categoria);
        productoDb.setFecActualizacion(LocalDateTime.now());
        return modelMapper.map(productoRepository.save(productoDb), ProductoResponseDTO.class);

    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoResponseDTO> findAll() {
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        List<ProductoResponseDTO> productosDTO = new ArrayList<>();
        productos.forEach(producto->productosDTO.add(modelMapper.map(producto, ProductoResponseDTO.class)));
        return productosDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductoResponseDTO findById(Long id) {
        Producto productoDb = productoRepository.findById(id).orElseThrow(()-> new DataNotFoundException("El producto no existe"));
        return modelMapper.map(productoDb, ProductoResponseDTO.class);
    }

    @Transactional
    @Override
    public ProductoResponseDTO delete(Long id) throws IOException {
        Producto productoDb = productoRepository.findById(id).orElseThrow(()-> new DataNotFoundException("El producto que desea eliminar no existe"));
        uploadService.deleteImage(uploadLocation, productoDb.getFoto());
        productoRepository.delete(productoDb);
        return modelMapper.map(productoDb, ProductoResponseDTO.class);
    }

    @Transactional
    @Override
    public ProductoResponseDTO guardarImagen(Long id, MultipartFile foto) throws IOException{
        Producto productoDb = productoRepository.findById(id).orElseThrow(()-> new DataNotFoundException("El producto no existe"));
        if(!uploadService.isValidImage(foto)){
            throw new NotImageFormatException("El archivo subido no es una imagen.");
        }

        String nombreFoto="producto_"+productoDb.getId()+".jpg";
        boolean result= uploadService.convertToJpg(uploadLocation, nombreFoto, foto);
        if (!result) {
            throw new NotImageFormatException("No se pudo escribir la imagen en formato JPG.");
        }

        uploadService.subirImagen(uploadLocation, nombreFoto, foto);
        productoDb.setFoto(nombreFoto);
        productoDb.setFecActualizacion(LocalDateTime.now());

        return modelMapper.map(productoRepository.save(productoDb), ProductoResponseDTO.class);
    }

    @Transactional
    @Override
    public ProductoResponseDTO eliminarImagen(Long id) throws IOException{
        Producto productoDb = productoRepository.findById(id).orElseThrow(()-> new DataNotFoundException("El producto no existe"));
        boolean result = uploadService.deleteImage(uploadLocation, productoDb.getFoto());
        if (!result) {
            throw new ImagenNotFoundException("El medidor no tiene imagen registrada en BD");
        }
        productoDb.setFoto(null);
        productoDb.setFecActualizacion(LocalDateTime.now());
        return modelMapper.map(productoRepository.save(productoDb), ProductoResponseDTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public byte[] generatePdfContent() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            List<Producto> productos = (List<Producto>) productoRepository.findAll();
            int nColumnas=4;
            PdfPTable tabla = new PdfPTable(nColumnas);

            tabla.addCell("Nombre");
            tabla.addCell("Stock");
            tabla.addCell("Precio c/u");
            tabla.addCell("Foto");

            for(Producto producto: productos){
                tabla.addCell(String.valueOf(producto.getNombre()));
                tabla.addCell(String.valueOf(producto.getStock()));
                tabla.addCell(String.valueOf(producto.getPrecio()));
                
                String imageName= producto.getFoto();
                

                try {
                    Resource resource = resourceLoader.getResource("file:"+ uploadLocation+ imageName);
                    File file = resource.getFile();

                    if (file.exists()) {
                        Image image = Image.getInstance(file.getAbsolutePath());
                        image.scaleToFit(152, 152);
                        tabla.addCell(image);
                    } else {
                        tabla.addCell(new Paragraph("[Imagen no encontrada]"));
                    }
                } catch (IOException e) {
                    tabla.addCell(new Paragraph("[Error al cargar imagen]"));
                    logger.error("Error generando el PDF", e);
                } catch (BadElementException e) {
                    tabla.addCell(new Paragraph("[Error al cargar imagen]"));
                    logger.error("Error generando el PDF", e);
                }

                
            }
            
            document.add(tabla);

            document.close();

            byte[] pdfBytes = baos.toByteArray();

            return pdfBytes;

        } catch (DocumentException e) {
            logger.error("Error generando el PDF", e);
        }
        return new byte[0];
        
    }

}
