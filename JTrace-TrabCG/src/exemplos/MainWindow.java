package exemplos;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.shader.Shaders;
import org.jtrace.swing.TracerPanel;

public class MainWindow extends JFrame {

  private static final long serialVersionUID = 8122517505454630633L;

  private Scene scene;
  
  public MainWindow(Scene scene) {
    setSize(700, 650);
    setTitle("JTrace");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.scene = scene;

    init();
  }

  private void init() {
    JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

    mainPanel.add(createTracerPanel());
    
    add(mainPanel);
  }

  private JPanel createTracerPanel() {
	Tracer tracer = new Tracer();
	
	tracer.addShaders(Shaders.ambientShader(), Shaders.diffuseShader());
	  
    return new TracerPanel(tracer, scene, new ViewPlane(500, 500), 500, 500);
  }
  
}
