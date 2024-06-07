package comp_net;

import java.awt.Color;
import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class LineEncoding extends ApplicationFrame {
    public LineEncoding(int[] input,double timee) {
        super("Signal");
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Signal" ,
                "Time" ,
                "Voltage" ,
                createDataset(input,timee) ,
                PlotOrientation.VERTICAL ,
                true , true , false);

        ChartPanel chartPanel = new ChartPanel( xylineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = xylineChart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 ,Color.ORANGE );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );
        super.pack( );
        super.setVisible( true );

    }

    private XYDataset createDataset(int[] input ,double timee) {
        final XYSeries dataa = new XYSeries( "input" );
        double time = 0;
        dataa.add( 0.0 , 0.0 );

        for (int i :input){
            dataa.add( time , i );
            time+=timee;
            dataa.add(time,i);
        }
        dataa.add(time,0);
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( dataa );
        return dataset;
    }
    public static void main(String[] args) {

    }
}
