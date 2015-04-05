import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;


public class Flightfind {

	private JFrame frmFlightFinder;
	private JTextField deparport;
	private JTextField arrivport;
	private JTextField depardate;
	private JTextField retdate;
	private JTextField lowtxtfld;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flightfind window = new Flightfind();
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					window.frmFlightFinder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Flightfind() {
		initialize();
	}

	
	private void initialize() {
		frmFlightFinder = new JFrame();
		frmFlightFinder.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\salman\\workspace\\SeleniumTest\\12321.jpg"));
		frmFlightFinder.setTitle("Flight Finder");
		frmFlightFinder.getContentPane().setForeground(new Color(46, 139, 87));
		frmFlightFinder.getContentPane().setFont(new Font("Tekton Pro Ext", Font.PLAIN, 11));
		frmFlightFinder.getContentPane().setBackground(new Color(205, 133, 63));
		frmFlightFinder.setForeground(Color.ORANGE);
		frmFlightFinder.setBackground(Color.DARK_GRAY);
		frmFlightFinder.setBounds(100, 100, 450, 300);
		frmFlightFinder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFlightFinder.getContentPane().setLayout(null);
		
		JLabel lblEnterThreeDigit = new JLabel("Enter Three Digit Code of Departure airport");
		lblEnterThreeDigit.setBounds(10, 36, 309, 14);
		frmFlightFinder.getContentPane().add(lblEnterThreeDigit);
		
		JLabel lblEnterThreeDigit_1 = new JLabel("Enter Three Digit code of arrival airport");
		lblEnterThreeDigit_1.setBounds(10, 71, 309, 14);
		frmFlightFinder.getContentPane().add(lblEnterThreeDigit_1);
		
		JLabel lblEnterDepartureDate = new JLabel("Enter departure date in format dd/mm/yyyy ");
		lblEnterDepartureDate.setBounds(10, 107, 309, 14);
		frmFlightFinder.getContentPane().add(lblEnterDepartureDate);
		
		JLabel lblEnterReturnDate = new JLabel("Enter return date in format dd/mm/yyyy ");
		lblEnterReturnDate.setBounds(10, 143, 309, 14);
		frmFlightFinder.getContentPane().add(lblEnterReturnDate);
		
		deparport = new JTextField();
		deparport.setBounds(342, 33, 86, 20);
		frmFlightFinder.getContentPane().add(deparport);
		deparport.setColumns(10);
		
		arrivport = new JTextField();
		arrivport.setBounds(342, 68, 86, 20);
		frmFlightFinder.getContentPane().add(arrivport);
		arrivport.setColumns(10);
		
		depardate = new JTextField();
		depardate.setBounds(342, 104, 86, 20);
		frmFlightFinder.getContentPane().add(depardate);
		depardate.setColumns(10);
		
		retdate = new JTextField();
		retdate.setBounds(342, 140, 86, 20);
		frmFlightFinder.getContentPane().add(retdate);
		retdate.setColumns(10);
		
		JButton btnFindLowestPrice = new JButton("Find lowest price!");
		btnFindLowestPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String depport = deparport.getText();
				String arriveport = arrivport.getText();
				String depdate = depardate.getText();
				String returndate = retdate.getText();
				
				
				try {
					String lowestprice = Flightfinder(depport,arriveport,depdate,returndate);
					
					
					
					System.out.println(lowestprice);
					lowtxtfld.setText(lowestprice);
				
				}
					catch (InterruptedException e1) {
					lowtxtfld.setText("Sorry could not find price");
					e1.printStackTrace();
				}
				
				
			}
		});
		btnFindLowestPrice.setBounds(259, 172, 169, 23);
		frmFlightFinder.getContentPane().add(btnFindLowestPrice);
		
		lowtxtfld = new JTextField();
		lowtxtfld.setBounds(342, 207, 86, 20);
		frmFlightFinder.getContentPane().add(lowtxtfld);
		lowtxtfld.setColumns(10);
	}
	
	public static String Flightfinder (String depport, String arrivport, String depdate, String retdate) throws InterruptedException{
		
		WebDriver driver;
		   String baseUrl;
		  
		   driver = new FirefoxDriver();
	       baseUrl = "http://www.expedia.ca/";
	    
	        //Date myDate = new Date();
			
			//String month = new SimpleDateFormat("MM").format(myDate);
			//String year = new SimpleDateFormat("yyyy").format(myDate);
			//String day = new SimpleDateFormat("dd").format(myDate);
			//int retudate = Integer.parseInt(month)+1;
			//String retmonth = String.valueOf(retudate);
	       
	       driver.get(baseUrl + "/Flights");
		    driver.findElement(By.id("F-origin")).clear();
		    driver.findElement(By.id("F-origin")).sendKeys(depport);
		    driver.findElement(By.id("F-fromDate")).clear();
		    driver.findElement(By.id("F-fromDate")).sendKeys(depdate);
		    // driver.findElement(By.id("F-fromDate")).sendKeys(day+"/"+month+"/"+year);
		    driver.findElement(By.id("F-destination")).clear();
		    driver.findElement(By.id("F-destination")).sendKeys(arrivport);
		    driver.findElement(By.id("F-toDate")).clear();
		    driver.findElement(By.id("F-toDate")).sendKeys(retdate);
		    //driver.findElement(By.id("F-toDate")).sendKeys(day+"/"+retmonth+"/"+year);
		    driver.findElement(By.id("F-searchButtonExt1")).click();
		    Thread.sleep(60000);
		    
		    try{
		    String s = driver.findElement(By.cssSelector("span.dollars.price-emphasis")).getText().replace("C", "").replace("$","");
		    System.out.println(s);
		    Thread.sleep(5000);
			driver.quit();
			return s;
		    }
		    catch(Exception g)
		    {System.out.println("Sorry, Could not find a price");
		    return "sorry";
		    }
		
		
	}
}
