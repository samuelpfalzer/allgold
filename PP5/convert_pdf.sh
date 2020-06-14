#!/bin/bash

echo 'converting ...'
java -cp saxon9he.jar net.sf.saxon.Transform -o:pdf_preisliste.fo produkt_export.xml pdf_preisliste.xsl
fop pdf_preisliste.fo preisliste.pdf
