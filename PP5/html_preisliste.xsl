<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
	<html>
		<head>
			<title>Allgold Preisliste</title>
			<link rel="stylesheet" href="preisliste.css"/>
		</head>

		<body>
			<h2>Allgold Preisliste</h2>

			<div class="main-container">
				<table border="0">
					<tr>
						<th>Artikelnummer</th>
						<th>Produktbezeichnung</th>
						<th>Preis</th>
					</tr>
					<xsl:for-each select="/resultset/row">
						<tr>
							<td> <xsl:value-of select="field[@name='id']"/> </td>
							<td> <xsl:value-of select="field[@name='name']"/> </td>
							<td> <xsl:value-of select="field[@name='preis']"/> </td>
						</tr>
					</xsl:for-each>
				</table>

				<h3>Produkte sortiert nach Produktname</h3>
				<table border="0">
					<tr>
						<th>Artikelnummer</th>
						<th>Produktbezeichnung</th>
						<th>Preis</th>
					</tr>
					<xsl:for-each select="/resultset/row/field[@name='name']">
						<xsl:sort order="ascending"/>
							<tr>
								<td> <xsl:value-of select="../field[@name='id']"/> </td>
								<td> <xsl:value-of select="../field[@name='name']"/> </td>
								<td> <xsl:value-of select="../field[@name='preis']"/> </td>
							</tr>
					</xsl:for-each>
				</table>

				<h3>Produkte absteigend sortiert nach Preis</h3>
				<table border="0">
					<tr>
						<th>Artikelnummer</th>
						<th>Produktbezeichnung</th>
						<th>Preis</th>
					</tr>
					<xsl:for-each select="/resultset/row/field[@name='preis']">
						<xsl:sort order="descending"/>
							<tr>
								<td> <xsl:value-of select="../field[@name='id']"/> </td>
								<td> <xsl:value-of select="../field[@name='name']"/> </td>
								<td> <xsl:value-of select="../field[@name='preis']"/> </td>
							</tr>
					</xsl:for-each>
				</table>
			</div>
		</body>
	</html>
</xsl:template>
</xsl:stylesheet>