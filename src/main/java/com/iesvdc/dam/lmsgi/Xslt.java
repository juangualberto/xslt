package com.iesvdc.dam.lmsgi;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.Serializer;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XsltCompiler;
import net.sf.saxon.s9api.XsltExecutable;
import net.sf.saxon.s9api.XsltTransformer;

import net.sf.saxon.BasicTransformerFactory;

/**
 * Uso del programa:
 *  Xsl fichero.xml fichero.xsl salida.html
 *
 */
public class Xslt 
{
    public static void main( String[] args )
    {
    	if (args.length==3) {
	    	try {
	    		// from S9APIExamples.java (S9HE)
		    	Processor proc = new Processor(false);
		        XsltCompiler comp = proc.newXsltCompiler();
		        System.out.println("Cargando hoja de estilo: "+args[1]);
		        XsltExecutable exp = comp.compile(new StreamSource(new File(args[1])));
		        System.out.println("Cargando XML: "+args[0]);
		        XdmNode source = proc.newDocumentBuilder().build(new StreamSource(new File(args[0])));
		        Serializer out = proc.newSerializer();
		        out.setOutputProperty(Serializer.Property.METHOD, "html");
		        out.setOutputProperty(Serializer.Property.INDENT, "yes");
		        out.setOutputFile(new File(args[2]));
		        XsltTransformer trans = exp.load();
		        trans.setInitialContextNode(source);
		        trans.setDestination(out);
		        trans.transform();
		        System.out.println("\n¡¡¡¡EXITO!!! Output written to "+args[2]+"\n\n");
	    	} catch (Exception ex) {
	    		System.out.println("Error:: "+ex.getMessage());
	    	}	        
    	}
    }
}
