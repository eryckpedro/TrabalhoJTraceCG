package classesExtendidas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
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
	
	private int resPixel;

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
		this.resPixel = 0;
		
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
	    
	    JPanel buttonArea = new JPanel(new FlowLayout());
	    
	    JRadioButton resOrig = new JRadioButton("Original");
	    resOrig.setSelected(true);
	    resOrig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 0;
				
			}
		});
	    
	    JRadioButton res2x2 = new JRadioButton("2x2");
	    res2x2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 2;
				
			}
		});
	    
	    JRadioButton res4x4 = new JRadioButton("4x4");
	    res4x4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 4;
				
			}
		});
	    
	    JRadioButton res8x8 = new JRadioButton("8x8");
	    res8x8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 8;
				
			}
		});
	    
	    JRadioButton res16x16 = new JRadioButton("16x16");
	    res16x16.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 16;
				
			}
		});
	    
	    JRadioButton res32x32 = new JRadioButton("32x32");
	    res32x32.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 32;
			
			}
		});
	    
	    JRadioButton res64x64 = new JRadioButton("64x64");
	    res64x64.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 64;
				
			}
		});
	    
	    JRadioButton res128x128 = new JRadioButton("128x128");
	    res128x128.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 128;
				
			}
		});
	    
	    JRadioButton res256x256 = new JRadioButton("256x256");
	    res256x256.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 256;
				
			}
		});
	    
	    JRadioButton res512x512 = new JRadioButton("512x512");
	    res512x512.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resPixel = 512;
				
			}
		});
	    
	    ButtonGroup group = new ButtonGroup();
	    group.add(resOrig);
	    group.add(res2x2);
	    group.add(res4x4);
	    group.add(res8x8);
	    group.add(res16x16);
	    group.add(res32x32);
	    group.add(res64x64);
	    group.add(res128x128);
	    group.add(res256x256);
	    group.add(res512x512);
	    
	    JButton renderBtn = new JButton("Render");
	    renderBtn.addActionListener(new ActionListener() {
	      @Override
	      public void actionPerformed(ActionEvent e) {
	        progressBar.setString(null);
	        
	        new Thread() {
	          @Override
	          public void run() {
	            tracer.render(scene, viewPlane, resPixel);
	          }
	        }.start();
	      }
	    });
	    
	    buttonArea.add(resOrig);
	    buttonArea.add(res2x2);
	    buttonArea.add(res4x4);
	    buttonArea.add(res8x8);
	    buttonArea.add(res16x16);
	    buttonArea.add(res32x32);
	    buttonArea.add(res64x64);
	    buttonArea.add(res128x128);
	    buttonArea.add(res256x256);
	    buttonArea.add(res512x512);
	    
	    JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    progressBar.setValue(0);
	    progressBar.setStringPainted(true);
	    
	    statusPanel.add(progressBar);
	    
	    JScrollPane scrollPane = new JScrollPane(drawablePanel);
	    
	    
	    add(statusPanel, BorderLayout.PAGE_END);
	    add(scrollPane, BorderLayout.CENTER);
	    //add(drawablePanel, BorderLayout.CENTER);
	    add(buttonArea, BorderLayout.PAGE_START);
	    add(renderBtn, BorderLayout.WEST);
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
