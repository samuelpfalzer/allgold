#!/bin/bash

echo 'converting ...'
java -cp saxon9he.jar net.sf.saxon.Transform -o:preisliste.html produkt_export.xml html_preisliste.xsl
