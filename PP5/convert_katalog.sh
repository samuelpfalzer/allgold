#!/bin/bash

echo 'PDF Katalog wird erstellt ...'
echo '  0% Datenbankexport ...'
docker exec allgold-db mysqldump --xml -utest -ptest -h localhost allgold > db_katalog.xml
echo ' 33% XSL Transformation ...'
java -cp saxon9he.jar net.sf.saxon.Transform -o:pdf_katalog.fo db_katalog.xml pdf_katalog.xsl
echo ' 66% PDF Datei wird erstellt ...'
fop pdf_katalog.fo katalog.pdf
echo '100% PDF Katalog erstellt ...'
