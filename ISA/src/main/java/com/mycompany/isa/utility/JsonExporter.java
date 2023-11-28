/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.isa.model.Lote;
import com.mycompany.isa.model.CategoriaIndicadores;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naoki
 */
public class JsonExporter {

    private static final String ROOT = "json";
    private static final String PATH_LOTE = String.join(File.separator, ROOT, "lotes");
    private static final String PATH_INDICADOR = String.join(File.separator, ROOT, "indicadores");
    private static final String JAR_PATH_DEFAULT_INDICADOR = "indicadores_padrao/";

    public static void createPaths() {
        if (Files.notExists(Paths.get(PATH_INDICADOR))) {
            try {
                System.out.println(JsonExporter.class.getResource(JAR_PATH_DEFAULT_INDICADOR));
                Files.createDirectories(Paths.get(PATH_LOTE));
                Files.createDirectories(Paths.get(PATH_INDICADOR));
                restaurarIndicadoresPadrao();
            } catch (IOException ex) {
                Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<Lote> importLotes() {
        ArrayList<Lote> lotes = new ArrayList<>();
        File loteDir = new File(PATH_LOTE);
        ObjectMapper mapper = new ObjectMapper();
        for (File f : loteDir.listFiles()) {
            try {
                Lote l = mapper.readValue(f, new TypeReference<Lote>() {
                });
                lotes.add(l);
            } catch (IOException ex) {
                Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lotes;
    }

    public static ArrayList<CategoriaIndicadores> importIndicadores() {
        ArrayList<CategoriaIndicadores> indicadores = new ArrayList<>();
        File indicadorDir = new File(PATH_INDICADOR);
        ObjectMapper mapper = new ObjectMapper();
        for (File f : indicadorDir.listFiles()) {
            CategoriaIndicadores i;
            try {
                i = mapper.readValue(f, new TypeReference<CategoriaIndicadores>() {
                });
                indicadores.add(i);
            } catch (IOException ex) {
                Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return indicadores;
    }

    public static void exportAllLotes(List<Lote> loteList) {
        ObjectMapper mapper = new ObjectMapper();

        for (Lote lote : loteList) {
            File resultFile = new File(PATH_LOTE, lote.getResponsavel() + "-" + lote.getNumParcela());
            try {
                mapper.writeValue(resultFile, lote);
            } catch (IOException ex) {
                Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void exportLote(Lote lote) {
        ObjectMapper mapper = new ObjectMapper();
        File resultFile = new File(PATH_LOTE, lote.getResponsavel() + "-" + lote.getNumParcela());
        resultFile.delete(); // delete file if exists
        try {
            mapper.writeValue(resultFile, lote);
        } catch (IOException ex) {
            Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void exportIndicadores(List<CategoriaIndicadores> indicadorList) {
        ObjectMapper mapper = new ObjectMapper();

        for (CategoriaIndicadores indicador : indicadorList) {
            File resultFile = new File(PATH_INDICADOR, indicador.getNome());
            try {
                mapper.writeValue(resultFile, indicador);
            } catch (IOException ex) {
                Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void deleteDirContent(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
    }

    public static void deleteLotes() {
        deleteDirContent(new File(PATH_LOTE));
    }

    public static void deleteIndicadores() {
        deleteDirContent(new File(PATH_INDICADOR));
    }

    public static void restaurarIndicadoresPadrao() {
        try {
            deleteIndicadores();
            copiarArquivosDoJarParaFora(JAR_PATH_DEFAULT_INDICADOR, PATH_INDICADOR);
        } catch (IOException ex) {
            Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(JsonExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String lerArquivoJAR(String caminho) throws IOException {
        var classLoader = JsonExporter.class;
        try (InputStream inputStream = classLoader.getResourceAsStream(caminho)) {
            if (inputStream == null) {
                throw new IOException("Arquivo não encontrado: " + caminho);
            }
            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    private static void copiarArquivosDoJarParaFora(String diretorioAlvoNoJar, String diretorioDestino) throws IOException, URISyntaxException {
        String caminhoJar = JsonExporter.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try (JarFile jarFile = new JarFile(caminhoJar)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().startsWith(diretorioAlvoNoJar)) {
                    Path entryDestination = Paths.get(diretorioDestino, entry.getName().substring(diretorioAlvoNoJar.length()));
                    if (entry.isDirectory()) {
                        Files.createDirectories(entryDestination);
                    } else {
                        try (InputStream entryStream = jarFile.getInputStream(entry)) {
                            Files.copy(entryStream, entryDestination, StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                }
            }
        }
    }
}
