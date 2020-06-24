<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">

<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	
	<fo:layout-master-set>
		<fo:simple-page-master
			master-name="Katalog"
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

	
	
	<fo:page-sequence master-reference="Katalog">
		<fo:flow flow-name="xsl-region-body">
			<fo:block background-image="andy-kelly.jpg" content-height="100%" content-width="100%" background-position-horizontal="center" background-position-vertical="center" scaling="uniform">
				<fo:block font-size="44pt" space-before="4cm" space-after="4cm" text-align="center" margin-top="4cm" color="white">
					Allgold Datenbankkatalog
				</fo:block>
			</fo:block>

			<fo:block id="inhalt" font-size="22pt" space-before="1.5cm" space-after="0.5cm" text-align="center" text-transform="uppercase">
				<fo:inline text-decoration="underline" font-weight="bold">
					Inhalt
					<fo:footnote>
						<fo:inline font-size="8pt" baseline-shift="super">1</fo:inline>
					
						<fo:footnote-body>
							<fo:block text-align-last="justify">
								<fo:leader leader-length="80%" rule-thickness="0.5pt" leader-pattern="rule"></fo:leader>
							</fo:block>
							<fo:block font-size="11pt" text-transform="none">
								<fo:inline font-size="8pt" baseline-shift="super">1</fo:inline>
								Fußnote
							</fo:block>
						</fo:footnote-body>
					</fo:footnote>
				</fo:inline>
			</fo:block>

			<xsl:for-each select="/mysqldump/database/table_data">
				<xsl:variable name="current_link" select="@name" />
				<fo:block space-before="0.5cm" font-size="11pt" text-align-last="justify">
					<fo:basic-link internal-destination="{$current_link}" show-destination="replace">
						<xsl:number />. 
						<xsl:value-of select="@name" />
						<fo:leader leader-pattern="space"  />
						Seite <fo:page-number-citation ref-id="{$current_link}" />
					</fo:basic-link>
				</fo:block>
			</xsl:for-each>

		</fo:flow>
	</fo:page-sequence>

	<xsl:for-each select="/mysqldump/database/table_data">
		<fo:page-sequence master-reference="Katalog">
			
			
			<fo:static-content flow-name="xsl-region-after">
				<fo:block text-align="center">
					&#x2014; Seite <fo:page-number /> &#x2014;
				</fo:block>
			</fo:static-content>
			
			<fo:flow flow-name="xsl-region-body">

				<xsl:variable name="current_link" select="@name" />
				
				<fo:block id="{$current_link}" font-size="22pt" text-align="center" space-before="5mm" space-after="10mm">
					<xsl:value-of select="@name" />
				</fo:block>
				
				<xsl:choose>
					<xsl:when test="count(descendant::row) = 0">
						<fo:block>
							Tabelle <xsl:value-of select="@name"/> hat keine Einträge
						</fo:block>
					</xsl:when>

					<xsl:otherwise>
						<fo:block>
							<fo:table border-style="solid" border-color="black" border-width="1px">
								<fo:table-header color="white" background-color="#039dfc">
									<fo:table-row>
										<xsl:for-each select="row[1]/field">
											<fo:table-cell>
												<fo:block>
													<xsl:value-of select="@name"/>
												</fo:block>
											</fo:table-cell>
										</xsl:for-each>
									</fo:table-row>
								</fo:table-header>
								
								<fo:table-footer font-size="8pt" text-align="right">
									<fo:table-row>
										<xsl:variable name="nr_cols" select="count(descendant::row[1]/field)"/>
										<fo:table-cell number-columns-spanned="{$nr_cols}">
											<fo:block>
												&#169; 2020 Allgold GmbH
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</fo:table-footer>

								<fo:table-body>
									<xsl:for-each select="row">
										<fo:table-row>
											<xsl:for-each select="field">
												<fo:table-cell>
													<fo:block><xsl:value-of select="."/></fo:block>
												</fo:table-cell>
											</xsl:for-each>
										</fo:table-row>
									</xsl:for-each>
								</fo:table-body>
							</fo:table>
						</fo:block>
					</xsl:otherwise>
				</xsl:choose>
				
				<fo:block font-size="11pt" space-before="0.5cm" space-after="0.5cm">
					<fo:inline text-decoration="underline">
						<fo:basic-link internal-destination="inhalt" show-destination="replace">
							Inhaltsverzeichnis
						</fo:basic-link>
					</fo:inline>
				</fo:block>
			</fo:flow>
		
		</fo:page-sequence>
	</xsl:for-each>

</fo:root>

</xsl:template>
</xsl:stylesheet>