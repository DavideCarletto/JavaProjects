package liceocuneo.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class CovidServiceImpl implements CovidService {

	private static final String url = "jdbc:mysql://didattica.liceocuneo.it:3306/db5F?serverTimezone=Europe/Rome";
	private static final String user = "quintaf";
	private static final String psd = "iwish10";

	@Override
	public ElencoInfo leggiCsv(File f) {
		ElencoInfo elencoInfo = new ElencoInfo();
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			br.readLine();
			int tampPrec = 0;
			do {

				line = br.readLine();
				if (line != null) {
					String[] infoS = line.split(",");
					Date data = Date.valueOf((infoS[0].substring(0, infoS[0].indexOf('T'))));
					int totPositivi = Integer.parseInt(infoS[6]);
					int nuoviPositivi = Integer.parseInt(infoS[8]);
					int numTamponi = (Integer.parseInt(infoS[14]) - tampPrec);
					tampPrec = Integer.parseInt(infoS[14]);
					Info info = new Info(data, totPositivi, nuoviPositivi, numTamponi);
					elencoInfo.aggiungiInfo(info);
				}

			} while (line != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elencoInfo;
	}

	@Override
	public void caricaDatiDb(ElencoInfo elencoInfo) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, psd);

			stmt = conn.prepareStatement(
					"INSERT INTO CD_InfoCovid (Data,Totale_Positivi,Nuovi_Positivi,Tamponi_Effettuati,Percentuale_Positivi_Tamponi) VALUES (?,?,?,?,?)");
			for (int i = 0; i < elencoInfo.size(); i++) {
				Info info = elencoInfo.get(i);
				stmt.setDate(1, info.getData());
				stmt.setInt(2, info.getTotPositivi());
				stmt.setInt(3, info.getNuoviPositivi());
				stmt.setInt(4, info.getNumTamponi());
				stmt.setFloat(5, ((float)info.getNuoviPositivi()/(float)info.getNumTamponi())*100);
				stmt.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JFreeChart getChart() {
		Connection conn = null;
		JFreeChart chart = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, psd);

			String query = "select Data,Totale_Positivi from CD_InfoCovid";
			JDBCCategoryDataset dataSet = new JDBCCategoryDataset(conn, query);
			chart = ChartFactory.createLineChart("Andamento_Contagi", "Data","Contagi",dataSet,PlotOrientation.VERTICAL,false,true,true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

}
