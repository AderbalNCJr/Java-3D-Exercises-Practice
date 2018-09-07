import javax.media.j3d.*;
import javax.vecmath.*;
import java.applet.*;
import java.awt.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;

public class ConeAndCube
{
        public static void main( String[] args ) {
                Frame frame = new Frame( );
                frame.setSize( 360, 180 );
                frame.setLayout( new BorderLayout( ) );

                Canvas3D c1 = new Canvas3D( SimpleUniverse
                        .getPreferredConfiguration() );
                frame.add( "Center", c1 );
                Canvas3D c2 = new Canvas3D( SimpleUniverse
                        .getPreferredConfiguration() );
                
              
                c1.setSize(90, 90);
                c1.setMonoscopicViewPolicy(View.LEFT_EYE_VIEW);
                frame.add(c1);
                
                c2.setSize(90, 90);
                c2.setLocation(90, 90);
                c2.setMonoscopicViewPolicy(View.RIGHT_EYE_VIEW);
                frame.add(c2);
                

                SimpleUniverse univ = new SimpleUniverse( c1 );
                univ.getViewingPlatform( ).setNominalViewingTransform( );
                
                SimpleUniverse univ2 = new SimpleUniverse( c2 );
                univ2.getViewingPlatform( ).setNominalViewingTransform( );

                BranchGroup scene = createSceneGraph( );
                scene.compile( );
                univ.addBranchGraph( scene );
                
                BranchGroup scene2 = createSceneGraph2( );
                scene2.compile( );
                univ2.addBranchGraph( scene2 );

                frame.show();;
        }

        private static BranchGroup createSceneGraph( )
        {
            // Make a scene graph branch
            BranchGroup branch = new BranchGroup( );

            // Make a changeable 3D transform
            TransformGroup trans = new TransformGroup( );
            trans.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
            branch.addChild( trans );

            // Make a shape
            ColorCube demo = new ColorCube( 0.4 );
            trans.addChild( demo );

            // Make a behavor to spin the shape
            Alpha spinAlpha = new Alpha( -1, 4000 );
            RotationInterpolator spinner =
                    new RotationInterpolator( spinAlpha, trans );
            spinner.setSchedulingBounds(
                    new BoundingSphere( new Point3d( ), 1000.0 ) );
            trans.addChild( spinner );

            return branch;
        }
        
        private static BranchGroup createSceneGraph2( )
        {
        	 // Make a scene graph branch
            BranchGroup branch = new BranchGroup( );

            // Make a changeable 3D transform
            TransformGroup trans2 = new TransformGroup( );
            trans2.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
            branch.addChild( trans2 );

            // Make a shape
            
            Appearance app = new Appearance();
            	ColoringAttributes ca = new ColoringAttributes();
            	ca.setColor(new Color3f(0.0f,1.0f,0.0f));
            	app.setColoringAttributes(ca);
            	
            Cone demo2 = new Cone(0.7f,0.3f, app);
            trans2.addChild(demo2);

            // Make a behavor to spin the shape
            Alpha spinAlpha = new Alpha( -1, 4000 );
            RotationInterpolator spinner =
                    new RotationInterpolator( spinAlpha, trans2 );
            spinner.setSchedulingBounds(
                    new BoundingSphere( new Point3d( ), 1000.0 ) );
            trans2.addChild( spinner );

            return branch;
        }
}