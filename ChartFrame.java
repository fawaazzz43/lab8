/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartFrame extends JFrame {

    public ChartFrame(String title, DefaultCategoryDataset dataset, String chartTitle) {
        setTitle(title);
        JFreeChart chart = ChartFactory.createBarChart(
                chartTitle,
                "Category",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
}
