<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">

<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	
	<fo:layout-master-set>
		<fo:simple-page-master
			master-name="Preisliste"
			page-height="297mm"
			page-width="210mm"
			margin-left="2.5cm"
			margin-right="2cm"
			margin-top="20mm"
			margin-bottom="2cm">
			<fo:region-body
				margin-top="2cm"
				margin-bottom="2cm"/>
			<fo:region-before extent="2cm"/>
			<fo:region-after extent="2cm"/>
		</fo:simple-page-master>
	</fo:layout-master-set>

	<fo:page-sequence master-reference="Preisliste">
		<fo:flow flow-name="xsl-region-body">
			<fo:block font-size="22pt" text-align="center" space-before="5mm" space-after="10mm">
				Allgold Preisliste
			</fo:block>
			<fo:block>
				<fo:table border-style="solid" border-color="black" border-width="1px">
					<fo:table-header color="white" background-color="#039dfc">
						<fo:table-row>
							<fo:table-cell>
								<fo:block>Artikelnummer</fo:block>
							</fo:table-cell>
							<fo:table-cell>
								<fo:block>Produktbezeichnung</fo:block>
							</fo:table-cell>
							<fo:table-cell>
								<fo:block>Preis</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-header>

					<fo:table-footer font-size="8pt" text-align="right">
						<fo:table-row>
							<fo:table-cell number-columns-spanned="3">
								<fo:block>
									&#169; 2020 Allgold GmbH
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-footer>

					<fo:table-body>
						<xsl:for-each select="/resultset/row">
						<fo:table-row>
							<fo:table-cell>
								<fo:block><xsl:value-of select="field[@name='id']"/></fo:block>
							</fo:table-cell>
							<fo:table-cell>
								<fo:block><xsl:value-of select="field[@name='name']"/></fo:block>
							</fo:table-cell>
							<fo:table-cell>
								<fo:block><xsl:value-of select="field[@name='preis']"/></fo:block>
							</fo:table-cell>
						</fo:table-row>
						</xsl:for-each>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:flow>
	</fo:page-sequence>

</fo:root>

</xsl:template>
</xsl:stylesheet>