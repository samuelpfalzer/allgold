#!/bin/bash

echo 'converting ...'
java -cp saxon9he.jar net.sf.saxon.Transform -o:pdf_katalog.fo db_katalog.xml pdf_katalog.xsl
fop pdf_katalog.fo katalog.pdf
