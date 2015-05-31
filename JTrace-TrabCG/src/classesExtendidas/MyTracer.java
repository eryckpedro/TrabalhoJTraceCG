package classesExtendidas;

import org.jtrace.Jay;
import org.jtrace.Scene;
import org.jtrace.Tracer;
import org.jtrace.ViewPlane;
import org.jtrace.cameras.Camera;
import org.jtrace.primitives.ColorRGB;

public class MyTracer extends Tracer {
	
	public void render(Scene scene, ViewPlane viewPlane, int resPixel)
	{
		final int hres = viewPlane.getHres();
        final int vres = viewPlane.getVres();
        final Camera camera = scene.getCamera();

        fireStart(viewPlane);
        initInterceptors(scene);
        
        if( resPixel > 0 )
        	pixelate(scene, resPixel, hres, vres, camera);
        else
        	classicRender(scene, hres, vres, camera);
        	

        fireFinish();
	}
	
	//original render method from JTrace
	private void classicRender(Scene scene, final int hres, final int vres,
			final Camera camera) {
		for (int r = 0; r < vres; r++) 
        {
            for (int c = 0; c < hres; c++) 
            {            	            	
                final Jay jay = camera.createJay(r, c, vres, hres);

                final ColorRGB color = trace(scene, jay);
                
                fireAfterTrace(color, c, r);
            }
        }
	}
	
	private void pixelate(Scene scene, int resPixel, final int hres,
			final int vres, final Camera camera) {
		
		int xFrag = vres / resPixel;
        int yFrag = hres / resPixel;
        
        for (int r = 0; r < vres; r+= xFrag) 
        {
            for (int c = 0; c < hres; c+= yFrag) 
            {            	            	
                final Jay jay = camera.createJay(r, c, vres, hres);

                final ColorRGB color = trace(scene, jay);
                
                for( int j = r; j < vres && j < r + yFrag; j++)
            	{
            		for( int i = c; i < hres && i < c + xFrag; i++)
            		{
            			fireAfterTrace(color, i, j);
            		}
            	}
            }
        }
	}

}
