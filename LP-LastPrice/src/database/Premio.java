package database;

public class Premio {
		private String[] fascia1;
		private String[] fascia2;
		private String[] fascia3;
		
		public Premio(){
			fascia1=new String[]{"Usb 16 gb Kingston","Casse Trust 2.1","Memoria SD 32Gb","Kit pulizia LCD","Cavo HDMI 3mt"};
			fascia2=new String[]{"Caricabatterie auto","Ebook Android 4","Supporto Universale Auto","Stampante InkJet Epson","Cuffie Sony"};
			fascia3=new String[]{"Hard Disk Esterno Samsung","Monitor LCD 22''Samsung","Stampante Fotografica portatile Canon","DockStation","Cuffie Bose"};
			
		}
		public int getRand(){
			int x = (int)Math.random() * 5;
			return x;
		}
		public String estrai(String s){
			String premio="";
			int num=this.getRand();
			if(s.compareToIgnoreCase("500-1000")==0) premio=fascia1[num];
			else if(s.compareToIgnoreCase("1000-2000")==0) premio=fascia2[num];
			else if(s.compareToIgnoreCase("2000-5000")==0) premio=fascia3[num];
			
			return premio;
			
		}
}
