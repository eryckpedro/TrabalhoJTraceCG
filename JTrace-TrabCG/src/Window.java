import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.interceptor.ShadowInterceptor;
import org.jtrace.shader.Shaders;
import org.jtrace.swing.TracerPanel;

import classesExtendidas.MyTracer;
import classesExtendidas.MyTracerPanel;


public class Window extends JFrame
{
	
	private static final long serialVersionUID = 8122517505454630633L;

	  private Scene scene;
	  
	  public Window(Scene scene) 
	  {
	    setSize(1080, 720);
	    setTitle("TrabalhoCG JTrace");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	    this.scene = scene;

	    init();
	  }

	  private void init() 
	  {
	    JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

	    mainPanel.add(createTracerPanel());
	    
	    add(mainPanel);
	  }

	  private JPanel createTracerPanel() 
	  {
		double kSpecular = 20;  
		MyTracer tracer = new MyTracer();
		
		tracer.addInterceptors(new ShadowInterceptor());
		tracer.addShaders(Shaders.ambientShader(), Shaders.diffuseShader(), Shaders.specularShader(kSpecular));
		  
	    return new MyTracerPanel(tracer, scene, new ViewPlane(800, 800), 800, 800);
	  }

}
