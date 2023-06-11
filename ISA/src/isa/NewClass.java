import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

class JTableEditableHeaderDemo implements Runnable
{
  private JTable table;
  private JTableHeader header;
  private JPopupMenu renamePopup;
  private JTextField text;
  private TableColumn column;

  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new JTableEditableHeaderDemo());
  }

  public JTableEditableHeaderDemo()
  {
    table = new JTable(10, 5);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());

    header = table.getTableHeader();
    header.addMouseListener(new MouseAdapter(){
      @Override
      public void mouseClicked(MouseEvent event)
      {
        if (event.getClickCount() == 2)
        {
          editColumnAt(event.getPoint());
        }
      }
    });

    text = new JTextField();
    text.setBorder(null);
    text.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        renameColumn();
      }
    });

    renamePopup = new JPopupMenu();
    renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
    renamePopup.add(text);
  }

  public void run()
  {
    JFrame f = new JFrame("Double-click header to edit");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(new JScrollPane(table));
    f.pack();
    f.setLocationRelativeTo(null);
    f.setVisible(true);
  }

    private void editColumnAt(Point p)
    {
        int columnIndex = header.columnAtPoint(p);

        if (columnIndex != -1)
        {
        column = header.getColumnModel().getColumn(columnIndex);
        Rectangle columnRectangle = header.getHeaderRect(columnIndex);

        text.setText(column.getHeaderValue().toString());
        renamePopup.setPreferredSize(
        new Dimension(columnRectangle.width, columnRectangle.height - 1));
        renamePopup.show(header, columnRectangle.x, 0);

        text.requestFocusInWindow();
        text.selectAll();
        }
    }

    private void renameColumn()
    {
        column.setHeaderValue(text.getText());
        renamePopup.setVisible(false);
        header.repaint();
    }
}
