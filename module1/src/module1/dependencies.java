/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * dependencies.java
 *
 * Created on 13 May, 2020, 2:49:33 PM
 */

package module1;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author DELL
 */




public class dependencies extends javax.swing.JFrame {

    /** Creates new form dependencies */

    String[] cols;
    String[] multi_attr;
    boolean multi;
    int len=1000000000;
    int no_of_keys = 0;

    public dependencies(String[] cols,boolean multi,String[] multi_attr) {
        initComponents();
        this.cols = cols;
        this.multi = multi;
        this.multi_attr = multi_attr;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */

    public String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public String closure(String attributes, String X, int no_of_fds, String fds[][])
    {
        //System.out.print(X+" ");
        String closure=X;
        String newclosure=X;
        do{
            closure=newclosure;
            for(int i=0;i<no_of_fds;i++)
            {
                boolean flag = true;
                boolean flag0 = true;
                boolean flag1 = true;
                for(int u=0;u<fds[i][0].length();u++)
                {
                    String temp=""+fds[i][0].charAt(u);
                    if(!attributes.contains(temp))
                    {
                        flag=false;
                        break;
                    }
                }

                for(int u=0;u<fds[i][1].length();u++)
                {
                    String temp=""+fds[i][1].charAt(u);
                    if(!attributes.contains(temp))
                    {
                        flag=false;
                        break;
                    }
                }

                for(int u=0;u<fds[i][0].length();u++)
                {
                    String temp=""+fds[i][0].charAt(u);
                    if(!newclosure.contains(temp))
                    {
                        flag0=false;
                        break;
                    }
                }

                for(int u=0;u<fds[i][1].length();u++)
                {
                    String temp=""+fds[i][1].charAt(u);
                    if(!newclosure.contains(temp))
                    {
                        flag1=false;
                        break;
                    }
                }


                if(flag0 && flag)
                {
                    if(flag1)
                        continue;
                    else
                    {
                        for(int h=0;h<fds[i][1].length();h++)
                        {
                            String st = "";
                            st+=fds[i][1].charAt(h);
                            if(!(newclosure.contains(st)))
                            {
                                newclosure+=st;
                            }
                        }
                        newclosure=sortString(newclosure);
                    }
                }
            }

        }while(!(closure.equals(newclosure)));


        boolean[] freq = new boolean[26];

        for(int i=0;i<26;i++)
            freq[i]=false;

        for(int i=0;i<newclosure.length();i++){

            freq[newclosure.charAt(i)-'a']=true;
        }

        closure="";

        for(int i=0;i<26;i++)
            if(freq[i])
                closure+=(char)('a'+i);
        //System.out.println(closure);
        return closure;
    }

    public String[] key(String attributes, String fds[][], int no_of_fds)
    {
        String[] key_list = new String[attributes.length()];
        String key=attributes;
//        String attributes1=attributes;
        int itr=attributes.length();
        int no_of_subsets=((itr)*(itr+1))/2;
        //String[][] fds = new String[no_of_fds][2];
        String[] subsets=new String[no_of_subsets];
        int subset_count=0;

        for(int len=1;len<=attributes.length(); len++)
        {
            for(int i=0;i<=attributes.length()-len;i++)
            {

                int j = i + len ;
                String curr="";

                for(int k=i;k<j;k++)
                    curr+=attributes.charAt(k);

                subsets[subset_count++]=curr;
            }
        }

//        for(int k=0;k<no_of_subsets;k++)
//        {
//            System.out.println(subsets[k]);
//        }

//        System.out.println();

        for(int k=0;k<no_of_subsets;k++)
        {
              String temp=sortString(subsets[k]);
//            itr=attributes.length();

//            for(int i=0;i<itr;i++)
//            {
//                String temp ="";
//
//                for(int j=0;j<key.length();j++)
//                {
//                    if(key.charAt(j)==attributes.charAt(i))
//                        continue;
//                    else
//                        temp+=key.charAt(j);
//                }
//
//              System.out.println(temp+" "+closure(temp,no_of_fds,fds)+" "+attributes);
              if((closure(attributes,temp,no_of_fds,fds)).equals(attributes))
                {
                    key=temp;
//                    System.out.println(temp + " " + closure(temp,no_of_fds,fds));
                    if(key.length()<=len)
                    {
                        key_list[no_of_keys] = key;
                        no_of_keys++;
                        len=key.length();
                    }
                    else
                    {
                        break;
                    }
                }
//            }
        }

        return key_list;
    }








    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TA2 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        TA1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jButton1.setBackground(new java.awt.Color(0, 153, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("->");

        TA2.setBackground(new java.awt.Color(153, 153, 153));
        TA2.setColumns(20);
        TA2.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        TA2.setRows(5);
        TA2.setBorder(null);
        jScrollPane2.setViewportView(TA2);

        TA1.setBackground(new java.awt.Color(153, 153, 153));
        TA1.setColumns(20);
        TA1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        TA1.setRows(5);
        TA1.setBorder(null);
        jScrollPane1.setViewportView(TA1);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 30));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter the Functional Dependencies");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(446, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(305, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(257, 257, 257))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))))
                .addGap(58, 58, 58)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addContainerGap(458, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int n = cols.length;
        char[] alpha = new char[n];
        String[] alphabet = new String[n];
        for(int i=0;i<n;i++) {
            alpha[i] = (char)(i+97);
            alphabet[i]= String.valueOf(alpha[i]);
            //System.out.println(alphabet[i]);
        }


        String[] multi_attri = new String[multi_attr.length];
        if(multi) {
            for(int i=0;i<multi_attr.length;i++) {
                System.out.println("1 "+multi_attr[i]);
                for(int j=0;j<n;j++) {
                    if(multi_attr[i].equals(cols[j])) {
                        multi_attri[i]=alphabet[j];
                        break;
                    }
                }
            }
        }

        String fundep1 = TA1.getText().toString();
        String fundep2 = TA2.getText().toString();
        String[] fdline1 = fundep1.split("\n",-1);
        String[] fdline2 = fundep2.split("\n",-1);

        int no_of_fds = fdline1.length;

        String[][] fds  = new String[no_of_fds][2];

        for(int i=0;i<no_of_fds;i++) {
            //System.out.println(fdline1[i]+" "+fdline2[i]);
            String[] attr = fdline1[i].split(",",-1);
            for(int j=0;j<attr.length;j++) {
                for(int k=0;k<n;k++) {
                    if(attr[j].equals(cols[k])) {
                        if(fds[i][0]==null) {
                            fds[i][0]=alphabet[k];
                        } else
                            fds[i][0]+=alphabet[k];
                    }
                }
            }
            //System.out.println(fds[i][0]);
        }


        for(int i=0;i<no_of_fds;i++) {
            String[] attr = fdline2[i].split(",",-1);
            for(int j=0;j<attr.length;j++) {
                for(int k=0;k<n;k++) {
                    if(attr[j].equals(cols[k])) {
                        if(fds[i][1]==null) {
                            fds[i][1]=alphabet[k];
                        } else
                            fds[i][1]+=alphabet[k];
                    }
                }
            }
        }

    /*for(int i=0;i<no_of_fds;i++)
    {
        for(int j=0;j<fds[i][0].length();j++)
        {
            System.out.print(fds[i][0].charAt(j));
        }

        System.out.print(" ");

        for(int j=0;j<fds[i][1].length();j++)
        {
            System.out.print(fds[i][1].charAt(j));
        }
        System.out.print("\n");
    }*/

        String alphas = "";

        for(int i=0;i<cols.length;i++) {
            alphas+=alphabet[i];
        }

        String[] key_list = key(alphas,fds,no_of_fds);

        dispose();
        result rs = new result(cols,multi,multi_attri,fds,no_of_fds,alphabet,key_list,no_of_keys);
        rs.setVisible(true);
}//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TA1;
    private javax.swing.JTextArea TA2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
