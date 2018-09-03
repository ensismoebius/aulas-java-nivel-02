import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entidadesBd.Filho;
import entidadesBd.Militante;
import entidadesBd.Tarefa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utils.hibernate.HibernateUtils;

public class Principal {

	public Principal() throws IOException {

		Militante ariovaldo = new Militante();
		ariovaldo.setNome("Ariovaldo da Silva Chavier");
		ariovaldo.setIdade(34);

		//ariovaldo.setFoto("/home/ensis/Imagens/eu_pb.jpg");
		ariovaldo.addFilhos(new Filho().setIdade(10).setNome("André"));
		ariovaldo.addFilhos(new Filho().setIdade(11).setNome("Julia"));
		ariovaldo.addFilhos(new Filho().setIdade(12).setNome("Denis"));
		ariovaldo.addFilhos(new Filho().setIdade(14).setNome("José"));
		ariovaldo.addFilhos(new Filho().setIdade(15).setNome("Jolie"));

		Tarefa semCap = new Tarefa().setDescricao("Livrar o mundo do capitalismo");
		Tarefa semMac = new Tarefa().setDescricao("Livrar o mundo do machismo");
		Tarefa semNac = new Tarefa().setDescricao("Livrar o mundo do nacionalismo");

		ariovaldo.addTarefa(semCap);
		ariovaldo.addTarefa(semMac);
		ariovaldo.addTarefa(semNac);

		Militante ariovalda = new Militante();
		ariovalda.setNome("Ariovalda da Silva Chavier");
		ariovalda.setIdade(34);

		ariovalda.addFilhos(new Filho().setIdade(14).setNome("André"));
		ariovalda.addFilhos(new Filho().setIdade(15).setNome("Julia"));
		ariovalda.addFilhos(new Filho().setIdade(16).setNome("Denis"));
		ariovalda.addFilhos(new Filho().setIdade(17).setNome("José"));
		ariovalda.addFilhos(new Filho().setIdade(18).setNome("Jolie"));

		ariovalda.addTarefa(semCap);
		ariovalda.addTarefa(semMac);
		ariovalda.addTarefa(semNac);
		// List<Filho> filhos = new ArrayList<Filho>();
		// filhos.add(f1);
		// filhos.add(f2);

		// ariovalda.setFilhos(filhos);

		EntityManagerFactory fabricaDeGerenciadorDeEntidades = Persistence.createEntityManagerFactory("PUTarde");

		EntityManager gerenciadorDeEntidade = fabricaDeGerenciadorDeEntidades.createEntityManager();

		gerenciadorDeEntidade.getTransaction().begin();

		gerenciadorDeEntidade.persist(ariovaldo);
		gerenciadorDeEntidade.persist(ariovalda);
		ariovaldo.setIdade(55);

		gerenciadorDeEntidade.merge(ariovaldo);

		gerenciadorDeEntidade.getTransaction().commit();
		gerenciadorDeEntidade.close();

		// Recuperando as pessoas

		
		EntityManager recuperador = fabricaDeGerenciadorDeEntidades.createEntityManager();
		Query sql = recuperador.createQuery("from Militante where cod = :parametro"); // where
																						// cod
																						// =
		sql.setParameter("parametro", new Long(1));

		// :parametro");
		List<Militante> listaDePessoas = sql.getResultList();

		for (Militante p : listaDePessoas) {
			p.enviarEmail("Vestibulinho", "Sifu");

			List<Filho> filhosRec = p.getFilhos();

			for (Filho f : filhosRec) {
				System.out.println(f.getNome());
			}

		}

		ImageIcon foto = listaDePessoas.get(0).getFoto();
		Image escalada = foto.getImage().getScaledInstance(100, 100, Image.SCALE_FAST);

		JLabel lblfoto = new JLabel(new ImageIcon(escalada));
		lblfoto.setSize(200, 200);

		JTextField txtNome = new JTextField(listaDePessoas.get(0).getNome());

		JFrame janela = new JFrame();
		janela.setLayout(new FlowLayout());

		janela.add(lblfoto);
		janela.add(txtNome);

		janela.pack();

		janela.setVisible(true);

		recuperador.close();

		// Hibernate Jasper integration

		gerenciadorDeEntidade = fabricaDeGerenciadorDeEntidades.createEntityManager();

		try {
			HashMap<String, Object> parametros = new HashMap<String, Object>();

			parametros.put("codResp", 45);
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("reports/hiber.jasper");
			JasperPrint print = JasperFillManager.fillReport(report, parametros, HibernateUtils.getJDBCConnectionObject(gerenciadorDeEntidade));
			JasperViewer.viewReport(print, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
		// session.close();
		System.exit(0);
	}

	public static void main(String[] args) {
		try {
			new Principal();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
