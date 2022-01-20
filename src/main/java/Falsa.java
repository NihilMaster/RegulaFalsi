import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.text.DecimalFormat;
import javax.swing.border.EmptyBorder;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author M
 */

public class Falsa extends JFrame{
    
    ////////////////////////////////////////////////////////////////
    /////////////La función se agrega a continuación////////////////
        ////////////////////////////////////////////////////////////////
    private static final String FUN_STR = "x^5 - 3x^2 - 1";
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    private static float f(float a){
        return (float)(Math.pow(a,5) - 3*Math.pow(a,2) - 1); 
    }
    ////////////////////////////////////////////////////////////////
    ////////////////////// No. Iteraciones /////////////////////////
    private static final int IT = 25;
    ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////
    
    JFrame frame;
    JPanel panelSuperior, panelInferior;
    JButton tabtt;
    JLabel descrip, albl, blbl, clbl, flbl;
    JTextField atxt, btxt, ctxt;
    JTable table;
    
    private static String fin = "";
    
    public Falsa() {
        super("Regula Falsi");
        
        // PANEL SUPERIOR
        panelSuperior = new JPanel ();
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelSuperior.setLayout(new GridLayout(3,2,8,8));
        albl = new JLabel("Ingrese el valor inferior:");
        atxt = new JTextField("0",20);
        blbl = new JLabel("Ingrese el valor superior:");
        btxt = new JTextField("0",20);
        clbl = new JLabel("Ingrese el n\u00FAmero de iteraciones:");
        ctxt = new JTextField("5",20);
        clbl.setVisible(true); ctxt.setVisible(true);
        panelSuperior.add(albl); panelSuperior.add(atxt);
        panelSuperior.add(blbl); panelSuperior.add(btxt);
        panelSuperior.add(clbl); panelSuperior.add(ctxt);
        
    
        // PANEL INFERIOR
        panelInferior= new JPanel();
        panelInferior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.PAGE_AXIS));
        tabtt = new JButton("Iterar");
        table = new JTable();
        flbl = new JLabel(""+FUN_STR);
        JScrollPane scrollPanel1 = new JScrollPane(table);
        // Estilo
        tabtt.setBackground(Color.MAGENTA);
        // Adición
        panelInferior.add(tabtt);
        panelInferior.add(scrollPanel1);
        panelInferior.add(flbl);
        
        // DESCRIPCIÓN
        descrip = new JLabel("Ingrese el intervalo y el n\u00FAmero de iteraciones.");
        descrip.setHorizontalAlignment(SwingConstants.LEFT);
        descrip.setFont(new Font("Arial", Font.BOLD, 20));
        
        // GENERAL
        frame =new JFrame("Regula Falsi     |     Mateo L. - Weimar J.");
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        frame.add(descrip);
        frame.add(panelSuperior);
        frame.add(panelInferior);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelSuperior.setBackground(Color.GRAY); panelInferior.setBackground(Color.GRAY);
        
        //Escuchadores
        tabtt.addActionListener((ActionEvent e) -> {
            String astr = atxt.getText();
            String bstr = btxt.getText();
            String cstr = ctxt.getText();
            if (Float.parseFloat(cstr) > IT){ showMessageDialog(null, "N\u00FAmero de iteraciones no soportado. M\u00E1ximo: "+IT); }
            else{
                Object[] col = {"i", "xi", "xs", "xa", "f(x1) * f(xa)", "error"};
                Object[][] dat = tab(Float.parseFloat(astr), Float.parseFloat(bstr), Float.parseFloat(cstr));
                DefaultTableModel dmt = new DefaultTableModel(dat,col);
                table.setModel(dmt);
                showMessageDialog(null, fin);
            }
            TableColumnModel ttam = table.getColumnModel();
            ttam.getColumn(0).setPreferredWidth(10);
            ttam.getColumn(1).setPreferredWidth(10);
            ttam.getColumn(2).setPreferredWidth(10);
            ttam.getColumn(3).setPreferredWidth(10);
            ttam.getColumn(4).setPreferredWidth(20);
            ttam.getColumn(5).setPreferredWidth(100);
        });
    }
    
    private static float xaxa(float a, float b){
        return (float)((a*f(b) - b*f(a))/(f(b)-f(a)));
    }
    
    private static String zzzz(float a, float x){
        float g1 = f(a); float g2 = f(x);
        if (g1>=0 && g2>=0 || g1<0 && g2<0){return "+";}
        else {return "-";}
    }
    
    private static float erro(float x, float xold){
        return (float)(100*Math.abs(x-xold)/x);
    }
    
    private static Object[][] tab(float a, float b, float iter){
        Object[][] s = new Object[IT][6];
        float aux = 0;
        for (int i=0; i<iter; i++){
            float x=xaxa(a,b);
            String z=zzzz(a,x);
            float e=erro(x,aux);
            System.out.println(i+1+"\t"+a+"\t"+b+"\t"+x+"\t"+z+"\t"+del(e)+"%");
            s[i][0]=(i+1); s[i][1]=a; s[i][2]=b; s[i][3]=x; s[i][4]=z; s[i][5]=del(e)+"%";
            if ("+".equals(z)){a=x;} else {b=x;}
            if (e == 0){break;}
            else {fin="La ra\u00EDz "+x+" se aproxima con un error de "+del(e)+"%";}
            // if (i+1==iter){fin="La ra\u00EDz "+x+" se aproxima con un error de "+del(e)+"%";}
            aux = x;
        }
        return s;
    }
    
    public static String del(double num) {
        String d = "###";
        return new DecimalFormat("#." + d + d + d).format(num);
    }
    
    public static void main(String[] args) throws IOException{
        Falsa run = new Falsa();
        System.out.println(""+run);
    }
}
