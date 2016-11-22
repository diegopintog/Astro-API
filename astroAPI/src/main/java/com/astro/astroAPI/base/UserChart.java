/**
 * 
 */
package com.astro.astroAPI.base;

/**
 * @author diego
 *
 */
public class UserChart {
	
	private BodyPosition sun      = new BodyPosition();
    private BodyPosition moon     = new BodyPosition();
    private BodyPosition mercury  = new BodyPosition();
    private BodyPosition venus    = new BodyPosition();
    private BodyPosition mars     = new BodyPosition();
    private BodyPosition jupiter  = new BodyPosition();
    private BodyPosition saturn   = new BodyPosition();
    private BodyPosition uranus   = new BodyPosition();
    private BodyPosition neptune  = new BodyPosition();
    private BodyPosition pluto    = new BodyPosition();
    private BodyPosition meanNode = new BodyPosition();
    private BodyPosition trueNode = new BodyPosition();

    public BodyPosition Sun()
    {
        return this.sun;
    }
    public BodyPosition Moon()
    {
        return this.moon;
    }
    public BodyPosition Mercury()
    {
        return this.mercury;
    }
    public BodyPosition Venus()
    {
        return this.venus;
    }
    public BodyPosition Mars()
    {
        return this.mars;
    }
    public BodyPosition Jupiter()
    {
        return this.jupiter;
    }
    public BodyPosition Saturn()
    {
        return this.saturn;
    }
    public BodyPosition Uranus()
    {
        return this.uranus;
    }
    public BodyPosition Neptune()
    {
        return this.neptune;
    }
    public BodyPosition Pluto()
    {
        return this.pluto;
    }
    public BodyPosition MeanNode()
    {
        return this.meanNode;
    }
    public BodyPosition TrueNode()
    {
        return this.trueNode;
    }

    public void gen_chart(double julianday)
    {
        sun.calc_coordinates(     julianday, 0);
        moon.calc_coordinates(    julianday, 1);
        mercury.calc_coordinates( julianday, 2);
        venus.calc_coordinates(   julianday, 3);
        mars.calc_coordinates(    julianday, 4);
        jupiter.calc_coordinates( julianday, 5);
        saturn.calc_coordinates(  julianday, 6);
        uranus.calc_coordinates(  julianday, 7);
        neptune.calc_coordinates( julianday, 8);
        pluto.calc_coordinates(   julianday, 9);
        meanNode.calc_coordinates(julianday, 10);
        trueNode.calc_coordinates(julianday, 11);
    }
}
