package com.ec.untilitario;

import com.ec.servicio.HelperPersistencia;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.w3c.dom.Node;
import org.zkoss.zk.ui.Executions;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import org.w3c.dom.DOMException;

import org.xml.sax.SAXException;

public class ArchivoUtils {

    public static String archivoToString(String rutaArchivo) {
        /*  70 */ StringBuffer buffer = new StringBuffer();
        try {
            /*  73 */ FileInputStream fis = new FileInputStream(rutaArchivo);
            /*  74 */ InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            /*  75 */ Reader in = new BufferedReader(isr);
            int ch;
            /*  77 */ while ((ch = in.read()) > -1) {
                /*  78 */ buffer.append((char) ch);
            }
            /*  80 */ in.close();
            /*  81 */ return buffer.toString();
        } catch (IOException e) {
            /*  83 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, e);
            /*  84 */        }
        return null;
    }

    public static File stringToArchivo(String rutaArchivo, String contenidoArchivo) {
        /*  97 */ FileOutputStream fos = null;
        /*  98 */ File archivoCreado = null;
        try {
            /* 102 */ fos = new FileOutputStream(rutaArchivo);
            /* 103 */ OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
            /* 104 */ for (int i = 0; i < contenidoArchivo.length(); i++) {
                /* 105 */ out.write(contenidoArchivo.charAt(i));
            }
            /* 107 */ out.close();

            /* 109 */ archivoCreado = new File(rutaArchivo);
        } catch (Exception ex) {
            int i;
            /* 112 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
            /* 113 */ return null;
        } finally {
            try {
                /* 116 */ if (fos != null) /* 117 */ {
                    fos.close();
                }
            } catch (Exception ex) {
                /* 120 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /* 123 */ return archivoCreado;
    }

    public static byte[] archivoToByte(File file)
            throws IOException {
        /* 136 */ byte[] buffer = new byte[(int) file.length()];
        /* 137 */ InputStream ios = null;
        try {
            /* 139 */ ios = new FileInputStream(file);
            /* 140 */ if (ios.read(buffer) == -1) /* 141 */ {
                throw new IOException("EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                /* 145 */ if (ios != null) /* 146 */ {
                    ios.close();
                }
            } catch (IOException e) {
                /* 149 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        /* 153 */ return buffer;
    }

    public static boolean byteToFile(byte[] arrayBytes, String rutaArchivo) {
        /* 164 */ boolean respuesta = false;
        try {
            /* 166 */ File file = new File(rutaArchivo);
            /* 167 */ file.createNewFile();
            /* 168 */ FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
            /* 169 */ ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayBytes);
            /* 170 */ OutputStream outputStream = new FileOutputStream(rutaArchivo);
            int data;
            /* 173 */ while ((data = byteArrayInputStream.read()) != -1) {
                /* 174 */ outputStream.write(data);
            }

            /* 177 */ fileInputStream.close();
            /* 178 */ outputStream.close();
            /* 179 */ respuesta = true;
        } catch (IOException ex) {
            /* 181 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* 183 */ return respuesta;
    }

   

    

    public static boolean adjuntarArchivo(File respuesta, File comprobante) {
        /* 507 */ boolean exito = false;
        try {
            /* 510 */ Document document = merge("*", new File[]{comprobante, respuesta});

            /* 512 */ DOMSource source = new DOMSource(document);

            /* 514 */ StreamResult result = new StreamResult(new OutputStreamWriter(new FileOutputStream(comprobante), "UTF-8"));

            /* 516 */ TransformerFactory transFactory = TransformerFactory.newInstance();
            /* 517 */ Transformer transformer = transFactory.newTransformer();

            /* 519 */ transformer.transform(source, result);
        } catch (Exception ex) {
            /* 522 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* 524 */ return exito;
    }

    private static Document merge(String exp, File[] files)
            throws Exception {
        /* 537 */ XPathFactory xPathFactory = XPathFactory.newInstance();
        /* 538 */ XPath xpath = xPathFactory.newXPath();
        /* 539 */ XPathExpression expression = xpath.compile(exp);

        /* 541 */ DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        /* 542 */ docBuilderFactory.setIgnoringElementContentWhitespace(true);
        /* 543 */ DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        /* 544 */ Document base = docBuilder.parse(files[0]);

        /* 546 */ Node results = (Node) expression.evaluate(base, XPathConstants.NODE);
        /* 547 */ if (results == null) {
            /* 548 */ throw new IOException(files[0] + ": expression does not evaluate to node");
        }

        /* 551 */ for (int i = 1; i < files.length; i++) {
            /* 552 */ Document merge = docBuilder.parse(files[i]);
            /* 553 */ Node nextResults = (Node) expression.evaluate(merge, XPathConstants.NODE);
            /* 554 */ results.appendChild(base.importNode(nextResults, true));
        }

        /* 563 */ return base;
    }

    public static boolean copiarArchivo(File archivoOrigen, String pathDestino) {
        /* 574 */ FileReader in = null;
        /* 575 */ boolean resultado = false;
        try {
            /* 578 */ File outputFile = new File(pathDestino);
            /* 579 */ in = new FileReader(archivoOrigen);
            /* 580 */ FileWriter out = new FileWriter(outputFile);
            int c;
            /* 582 */ while ((c = in.read()) != -1) {
                /* 583 */ out.write(c);
            }
            /* 585 */ in.close();
            /* 586 */ out.close();
            /* 587 */ resultado = true;
        } catch (Exception ex) {
            /* 590 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                /* 593 */ in.close();
            } catch (IOException ex) {
                /* 595 */ Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /* 598 */ return resultado;
    }

    public static void FileCopy(String sourceFile, String destinationFile) {
        System.out.println("Desde: " + sourceFile);
        System.out.println("Hacia: " + destinationFile);

        try {
            File inFile = new File(sourceFile);
            File outFile = new File(destinationFile);

            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);

            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Hubo un error de entrada/salida!!!");
        }
    }

    public static String zipFile(File archivo, String pathArchivo) {
        try {

            ZipFile zipFile = new ZipFile(pathArchivo.replace(".xml", ".zip"));
            ZipParameters parameters = new ZipParameters();

            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);

            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            zipFile.addFile(archivo, parameters);
            return pathArchivo.replace(".xml", ".zip");
        } catch (ZipException ex) {
            ex.getStackTrace();
        }
        return "";
    }

    public static void reporteGeneralPdfMail(String pathPDF, Integer numeroFactura) throws JRException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, NamingException {
        EntityManager emf = HelperPersistencia.getEMF();
        Connection con = null;
        try {
            String reportFile = Executions.getCurrent().getDesktop().getWebApp()
                    .getRealPath("/reportes");
            String reportPath = "";
            emf.getTransaction().begin();
            con = emf.unwrap(Connection.class);

            reportPath = reportFile + File.separator + "cotizacion.jasper";
            Map<String, Object> parametros = new HashMap<String, Object>();

            //  parametros.put("codUsuario", String.valueOf(credentialLog.getAdUsuario().getCodigoUsuario()));
            parametros.put("numcotizacion", numeroFactura);

            if (con != null) {
                System.out.println("Conexión Realizada Correctamenteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
            }
            FileInputStream is = null;
            is = new FileInputStream(reportPath);

//                byte[] buf = JasperRunManager.runReportToPdf(is, parametros, con);
            JasperPrint print = JasperFillManager.fillReport(reportPath, parametros, con);
            JasperExportManager.exportReportToPdfFile(print, pathPDF);
            
        } catch (FileNotFoundException e) {
            System.out.println("Error en generar el reporte file " + e.getMessage());
        } catch (JRException e) {
            System.out.println("Error en generar el reporte JRE  " + e.getMessage());
        } finally {
            if (emf != null) {
                emf.close();
                System.out.println("cerro entity");
            }
        }

    }

    /*AGREGA LO DESEADO AL FINAL DEL TAG REALIZA UN INCREMENT MAS NO UN ADD EN UNA POSICION ESPECIFICA*/
    public static void modificarXMLAutorizado(String URI, String estado, String autorizacion, Date fecha, String URISalida) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            // 1. cargar el XML original
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(URI));
            /*AGREGAMOS EL ESTODO, AUTORIZACION Y FECHA DE AUTORIZACION*/
            //Le añadimos una característica
            Element tagEsadto = doc.createElement("estado");
            //Le añadimos su valor
            Text valor = doc.createTextNode(estado);

            Element numeroAutorizacion = doc.createElement("numeroAutorizacion");
            //Le añadimos su valor
            Text valorAutorizacion = doc.createTextNode(autorizacion);

            Element fechaAutorizacion = doc.createElement("fechaAutorizacion");
            //Le añadimos su valor
            Text valorFecha = doc.createTextNode(formato.format(fecha));

// 2. buscar y eliminar el elemento <enfermera id="3"> de entre 
//    muchos elementos <enfermera> ubicados en cualquier posicion del documento
            NodeList items = doc.getElementsByTagName("factura");
            for (int ix = 0; ix < items.getLength(); ix++) {
                Element element = (Element) items.item(ix);
                Node cnode = items.item(ix);
//                if (cnode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element elem = (Element) cnode; // 'elem' Element after which the insertion should be made
//                    if (elem.getAttribute("name").equals("factura")) {
//                        Element newElement = doc.createElement("estado"); // Element to be inserted 
//                        newElement.setAttribute("name", "AUTORIZADO");
//                        // CODE HERE
//                    }
//                }
                System.out.println("imprimir --> " + element.getAttribute("id"));
                element.getParentNode().insertBefore(tagEsadto, element.getNextSibling());
                element.insertBefore(tagEsadto, element);
                element.appendChild(tagEsadto);
                tagEsadto.appendChild(valor);
                element.appendChild(numeroAutorizacion);
                numeroAutorizacion.appendChild(valorAutorizacion);
                element.appendChild(fechaAutorizacion);
                fechaAutorizacion.appendChild(valorFecha);
            }

// 3. Exportar nuevamente el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            Result output = new StreamResult(new File(URISalida));
            Source input = new DOMSource(doc);
            transformer.transform(input, output);
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (DOMException e) {
            System.out.println("ERROR " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    public static String generaraClaveTemporal() {
        int claveTemporal = 0;

        try {
            Random rng = new Random();
            claveTemporal = rng.nextInt(900) + 100; //siempre 3 digitos
        } catch (Exception e) {
            System.out.println("ERROR AL GENERAR CLAVE EN UTILITARI0 " + e.getMessage());
        }
        return ("" + claveTemporal).trim();
    }
}
