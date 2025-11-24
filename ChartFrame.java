package lab7;

import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ChartFrame extends JFrame {

    public ChartFrame(String chartTitle, String xLabel, String yLabel, Map<String, Double> data, Instructor instructor) throws IOException {
        super(chartTitle);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), yLabel, entry.getKey());
        }

       
        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle,
                xLabel,
                yLabel,
                dataset
        );

        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 450));

        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            
            InstructorDashboardFrame i = null;
            try {
                i = new InstructorDashboardFrame(instructor);
            } catch (IOException ex) {
                System.getLogger(ChartFrame.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            i.setVisible(true);
           
            this.dispose();
        });

        
        JPanel panelBottom = new JPanel();
        panelBottom.add(btnBack);

        
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);

        
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}