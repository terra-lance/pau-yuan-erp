package com.terrase.frame.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.terrase.frame.report.Report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

public class JasperReportUtil {
	static final Logger LOGGER = Logger.getLogger(JasperReportUtil.class);

	private final String DATASOURCE = "jdbc/terrase";
	private Connection conn;

	@SuppressWarnings("rawtypes")
	public void generateReportPrinter(Report report, Class anchorClassParam, String printerNameString)
			throws FileNotFoundException, JRException, Throwable {
		openConnection();

		InputStream templateStream = null;
		templateStream = anchorClassParam.getResourceAsStream(report.getTemplateFileName());
		JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, report.getParameters(), conn);

		PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
		printRequestAttributeSet.add(new Copies(1));

		PrinterName printerName = new PrinterName(printerNameString, null);

		PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
		printServiceAttributeSet.add(printerName);

		JRPrintServiceExporter exporter = new JRPrintServiceExporter();

		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
		configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
		configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
		configuration.setDisplayPageDialog(false);
		configuration.setDisplayPrintDialog(false);
		exporter.setConfiguration(configuration);
		exporter.exportReport();

		closeConnection();
	}

	@SuppressWarnings("rawtypes")
	public JasperPrint generateReportJasperPrint(Report report, Class anchorClassParam)
			throws FileNotFoundException, JRException, Throwable {
		openConnection();

		InputStream templateStream = null;
		templateStream = anchorClassParam.getResourceAsStream(report.getTemplateFileName());
		JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, report.getParameters(), conn);

		closeConnection();

		return jasperPrint;
	}

	@SuppressWarnings("rawtypes")
	public String generateReportPdf(Report report, Class anchorClassParam, String outputPath)
			throws FileNotFoundException, JRException, Throwable {
		openConnection();

		Files.createDirectories(Paths.get(outputPath));

		InputStream templateStream = null;
		templateStream = anchorClassParam.getResourceAsStream(report.getTemplateFileName());
		JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

		String extension = ".pdf";
		File destinationFile = new File(outputPath, report.getOutputFileName() + extension);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, report.getParameters(), conn);

		JasperExportManager.exportReportToPdfFile(jasperPrint, destinationFile.getPath());
		closeConnection();

		delete(1, extension, outputPath);

		return destinationFile.getPath();
	}

	@SuppressWarnings("rawtypes")
	public String generateReportXls(Report report, Class anchorClassParam, String outputPath)
			throws FileNotFoundException, JRException, Throwable {
		openConnection();

		Files.createDirectories(Paths.get(outputPath));

		InputStream templateStream = null;
		templateStream = anchorClassParam.getResourceAsStream(report.getTemplateFileName());
		JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

		String extension = ".xls";
		File destinationFile = new File(outputPath, report.getOutputFileName() + extension);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, report.getParameters(), conn);

		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destinationFile));

		exporter.exportReport();

		closeConnection();

		delete(1, extension, outputPath);

		return destinationFile.getPath();
	}

	@SuppressWarnings("rawtypes")
	public String generateReportCsv(Report report, Class anchorClassParam, String outputPath)
			throws FileNotFoundException, JRException, Throwable {
		openConnection();

		Files.createDirectories(Paths.get(outputPath));

		InputStream templateStream = null;
		templateStream = anchorClassParam.getResourceAsStream(report.getTemplateFileName());
		JasperReport jasperReport = JasperCompileManager.compileReport(templateStream);

		String extension = ".csv";
		File destinationFile = new File(outputPath, report.getOutputFileName() + extension);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, report.getParameters(), conn);

		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleWriterExporterOutput(destinationFile));

		exporter.exportReport();

		closeConnection();

		delete(1, extension, outputPath);

		return destinationFile.getPath();
	}

	private void openConnection() throws Throwable {
		Context initContext = new InitialContext();
		Context webContext = (Context) initContext.lookup("java:/comp/env");
		DataSource dataSource = (DataSource) webContext.lookup(DATASOURCE);
		conn = dataSource.getConnection();
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			LOGGER.error("Report close connection error", ex);
		}
	}

	private void delete(long days, String fileExtension, String dirPath) {
		try {
			File folder = new File(dirPath);

			if (folder.exists()) {
				File[] listFiles = folder.listFiles();
				long eligibleForDeletion = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L);

				for (File listFile : listFiles) {
					if (listFile.getName().endsWith(fileExtension) && listFile.lastModified() < eligibleForDeletion) {
						listFile.delete();
					}
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Cleaning export error", ex);
		}
	}
}