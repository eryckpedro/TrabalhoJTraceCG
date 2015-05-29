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
        
        //int resPixel = 16;
        
        int xFrag = vres / resPixel;
        int yFrag = hres / resPixel;
        
        
        for (int r = 0; r < vres; r+= xFrag) 
        {
            for (int c = 0; c < hres; c+= yFrag) 
            {            	            	
                final Jay jay = camera.createJay(r, c, vres, hres);

                final ColorRGB color = trace(scene, jay);
                
            	for( int i = r; i < vres; i++)
            	{
            		for( int j = c; j < hres; j++)
            		{
            			fireAfterTrace(color, j, i);
            		}
            	}
            }
        }

        fireFinish();
	}

}
