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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace LabShell
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
		private List<User> list = new List<User>();
        private List<string> selectUid = new List<string>();
		
        public MainWindow()
        {
            InitializeComponent();
            this.DataBinding();
        }
		
		private void DataBinding()  
		{
			for(int i =0 ; i < 5; i++)
			{
				User user = new User()
				{
					Uid = "12345"+i,
					Username = "panyan"+i
					
				};
                this.list.Add(user);
			}
            this.MemberList.ItemsSource = this.list;
		}

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            foreach (string uid in selectUid)
            {
                foreach (User user in list)
                {
                    if (user.Uid == uid)
                    {
                        this.list.Remove(user);
                        break;
                    }
                }
            }
            this.MemberList.Items.Refresh();
            List<CheckBox> cbs= FindFirstVisualChild<CheckBox>(this.MemberList, "titlecb");
            cbs.ElementAt(0).IsChecked = false;
        }

        private void CheckBox_Click(object sender, RoutedEventArgs e)
        {
            CheckBox cb = sender as CheckBox;
            string uid = cb.Tag.ToString();
            if (cb.IsChecked == true)
            {
                selectUid.Add(uid);
            }
            else
            {
                selectUid.Remove(uid);
            }
        }

        private void CheckBox_Click_1(object sender, RoutedEventArgs e)
        {
            CheckBox cb = sender as CheckBox;
            if (cb.IsChecked == true)
            {

                selectUid = list.Select(l => l.Uid).ToList();
                List<CheckBox> checkbox = FindFirstVisualChild<CheckBox>(this.MemberList, "checkbox");
                foreach(CheckBox c in checkbox)
                {
                    c.IsChecked = true;   
                }
            }
            else
            {
                selectUid.Clear();
                List<CheckBox> checkbox = FindFirstVisualChild<CheckBox>(this.MemberList, "checkbox");
                foreach (CheckBox c in checkbox)
                {
                    c.IsChecked = false;
                }
            } 
        }

        public List<T> FindFirstVisualChild<T>(DependencyObject obj, string childName) where T : DependencyObject
        {
            List<T> result = new List<T>();
            for (int i = 0; i < VisualTreeHelper.GetChildrenCount(obj); i++)
            {
                DependencyObject child = VisualTreeHelper.GetChild(obj, i);
                if (child != null && child is T && child.GetValue(NameProperty).ToString() == childName)
                {
                    result.Add((T)child);
                }
                else
                {
                    List<T> childOfChild = FindFirstVisualChild<T>(child, childName);
                    if (childOfChild.Count != 0)
                    {
                        result.AddRange(childOfChild);
                    }
                }
            }
            return result;
        }

        private void ResetButton_Click(object sender, RoutedEventArgs e)
        {

        }

        
    }
	
	public class User
	{
		string uid;
		string username;
		
		public string Uid
		{
			get{return uid;}
			set{ uid = value;}
		}
		
		public string Username
		{
			get{return username;}
			set{ username = value;}
		}
	}
}
