using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.Windows.Forms;

namespace LabShell
{
    /// <summary>
    /// ConfigWindow.xaml 的交互逻辑
    /// </summary>
    public partial class ConfigWindow : Window
    {
		private List<ListenPath> list = new List<ListenPath>();
		
        public ConfigWindow()
        {
            InitializeComponent();
            this.DataBinding();
        }

        public void DataBinding() 
        {
            //for (int i = 0; i < 5; i++)
            //{
            //    ListenPath listenpath = new ListenPath()
            //    {
                    
            //        Path = "C:\\" + i

            //    };
            //    this.list.Add(listenpath);
            //}
            this.listbox.ItemsSource = this.list;
        }
		
		private void Button_Click(object sender, RoutedEventArgs e)
        {
            System.Windows.Controls.Button btn = sender as System.Windows.Controls.Button;
            foreach (ListenPath lp in list)
            {
                string tag =  btn.Tag.ToString();
                if (lp.Path == tag)
                {
                    this.list.Remove(lp);
                    break;
                }
            }
            this.listbox.Items.Refresh();
            
		}

        private void BrowserButton_Click(object sender, RoutedEventArgs e)
        {
            FolderBrowserDialog m_Dialog = new FolderBrowserDialog();
            DialogResult result = m_Dialog.ShowDialog();

            if (result == System.Windows.Forms.DialogResult.Cancel)
            {
                return;
            }
            string m_Dir = m_Dialog.SelectedPath.Trim();
            this.PathText.Text = m_Dir;
        }

        private void AddButton_Click(object sender, RoutedEventArgs e)
        {
            
            this.list.Add(new ListenPath() { Path=this.PathText.Text});
            this.listbox.Items.Refresh();
            this.PathText.Text = "";
        }
        
    }
	
	public class ListenPath
	{
		string path;
        
		
		public string Path
		{
			get{return path;}
			set{path = value;}
		}

        
	}
}
