package classesExtendidas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.swing.DrawablePanel;
import org.jtrace.swing.SwingListener;
import org.jtrace.swing.TracerPanel;

public class MyTracerPanel extends TracerPanel {

	private static final long serialVersionUID = -5825078637417276490L;

	private MyTracer tracer;
	private Scene scene;
	private ViewPlane viewPlane;

	private JProgressBar progressBar;

	private int height;
	private int width;
	private DrawablePanel drawablePanel;

	public MyTracerPanel(MyTracer tracer, Scene scene, ViewPlane viewPlane) {
		super(tracer, scene, viewPlane, 300, 300);
	}

	public MyTracerPanel(MyTracer tracer, Scene scene, ViewPlane viewPlane,
			int width, int height) {
		super(tracer, scene, viewPlane, width, height);

		this.tracer = tracer;
		this.scene = scene;
		this.viewPlane = viewPlane;
		this.width = width;
		this.height = height;
		
		this.progressBar = new JProgressBar(0, width * height);

		setLayout(new BorderLayout());
		prepareAspectRatio();
		init();
	}

	private void prepareAspectRatio() {
	    float aspect = (float) viewPlane.getHres() / (float) viewPlane.getVres(); 
	    int newheight = Math.round(height / aspect);
	    
	    setPreferredSize(new Dimension(width, newheight));
	  }

	  private void init() {
	    drawablePanel = new DrawablePanel();
	    
	    SwingListener listener = new SwingListener(this);
	    tracer.addListeners(listener);
	    
	    JButton renderBtn = new JButton("Render");
	    renderBtn.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        progressBar.setString(null);
	        
	        new Thread() {
	          @Override
	          public void run() {
	            tracer.render(scene, viewPlane, 64);
	          }
	        }.start();
	      }
	    });
	    
	    JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    progressBar.setValue(0);
	    progressBar.setStringPainted(true);
	    
	    statusPanel.add(progressBar);
	    
	    JScrollPane scrollPane = new JScrollPane(drawablePanel);
	    
	    add(statusPanel, BorderLayout.PAGE_END);
	    add(scrollPane, BorderLayout.CENTER);
	    //add(drawablePanel, BorderLayout.CENTER);
	    add(renderBtn, BorderLayout.PAGE_START);
	  }
	  
	  public DrawablePanel getDrawablePanel() {
	    return drawablePanel;
	  }
	  
	  public void pixelsPainted(int pixels) {
	    progressBar.setValue(pixels);
	  }
	  
	  public void resetProgressBar() {
	    progressBar.setString("Inactive");
	  }

}
