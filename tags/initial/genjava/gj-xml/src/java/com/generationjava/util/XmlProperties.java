/*
 * Copyright (c) 2003, Henri Yandell
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the 
 * following conditions are met:
 * 
 * + Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 * 
 * + Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 * 
 * + Neither the name of Genjava-Core nor the names of its contributors 
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
// XmlProperties.java
package com.generationjava.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Properties;
import java.util.Enumeration;

import com.generationjava.io.xml.XMLParser;
import com.generationjava.io.xml.XMLNode;

/**
 */
public class XmlProperties extends Properties {

    static public XmlProperties load(File file) {
        XmlProperties props = new XmlProperties();
        return props;
    }

    public XmlProperties() {
        super();
    }

    public XmlProperties(Properties props) {
        super(props);
    }

    public void load(InputStream in) throws IOException {
        InputStreamReader reader = new InputStreamReader(in);
        this.load( reader );
        reader.close();
    }

    public void load(Reader reader) throws IOException {
        XMLParser parser = new XMLParser();
        XMLNode root = parser.parseXML(reader);
        Enumeration enum = root.enumerateNode();
        while(enum.hasMoreElements()) {
            XMLNode node = (XMLNode)enum.nextElement();
            add("", node);
        }
    }
    
    public void add(String level, XMLNode node) {
        if( node.getValue() != null ) {
            setProperty( level+node.getName(), node.getValue());
        }
        Enumeration attrs = node.enumerateAttr();
        if(attrs != null) {
            while(attrs.hasMoreElements()) {
                String attr = (String)attrs.nextElement();
                setProperty( level+node.getName()+"."+attr, node.getAttr(attr));
            }
        }
        Enumeration nodes = node.enumerateNode();
        if(nodes != null) {
            while(nodes.hasMoreElements()) {
                XMLNode subnode = (XMLNode)nodes.nextElement();
                add(level+subnode.getName()+".", subnode);
            }
        }
    }
    
    public Object setProperty(String key, String value) {
        return put( key, value );
    }
 
    // has to make sure not to write out any defaults
    public void save(OutputStream outstrm, String header) {
        super.save(outstrm,header);
    }
    // has to make sure not to write out any defaults
    public void store(OutputStream outstrm, String header) throws IOException {
        super.store(outstrm,header);
    }
}