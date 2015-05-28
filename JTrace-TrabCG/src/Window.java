import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.interceptor.ShadowInterceptor;
import org.jtrace.shader.Shaders;
import org.jtrace.swing.TracerPanel;


public class Window extends JFrame
{
	
	private static final long serialVersionUID = 8122517505454630633L;

	  private Scene scene;
	  
	  public Window(Scene scene) 
	  {
	    setSize(800, 600);
	    setTitle("TrabalhoCG JTrace");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
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
		double kSpecular = 10;  
		Tracer tracer = new Tracer();
		
		tracer.addInterceptors(new ShadowInterceptor());
		tracer.addShaders(Shaders.ambientShader(), Shaders.diffuseShader(), Shaders.specularShader(kSpecular));
		  
	    return new TracerPanel(tracer, scene, new ViewPlane(500, 500), 600, 400);
	  }

}
