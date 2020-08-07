/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package module1;
/**
 *
 * @author satvik
 */

import java.util.*;
import java.lang.*;

public class Main_1
{
    static boolean[] BCNFvis = new boolean[26];


    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static String closure(String attributes, String X, int no_of_fds, String fds[][])
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

    public static String key(String attributes, String fds[][], int no_of_fds)
    {
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
              if((closure(attributes,temp,no_of_fds,fds)).equals(attributes) && key.length()>temp.length())
                {
                    key=temp;
//                    System.out.println(temp + " " + closure(temp,no_of_fds,fds));
                    break;
                }
//            }
        }

        return key;
    }


    public static boolean convto1NF(String attributes, String Key,Scanner sc)
    {
        System.out.println("Are there any multivalued attributes?(y/n)");
        char ch=sc.next().charAt(0);
        sc.nextLine();
        if(ch=='y')
        {
            System.out.println("Unnormalized table\nEnter multivalued attributes to convert to 1NF:");
            String multi_attributes=sc.nextLine();

            boolean[] freq = new boolean[26];

            for(int i=0;i<26;i++)
                freq[i]=false;

            for(int i=0;i<multi_attributes.length();i++)
                freq[multi_attributes.charAt(i)-'a']=true;

            String temp="";

            for(int i=0;i<attributes.length();i++)
            {
                if(freq[attributes.charAt(i)-'a'])
                    continue;
                else
                    temp+=attributes.charAt(i);
            }

            attributes=temp;
            System.out.println("1NF:\nTable 1: "+attributes);
            for(int i=0;i<multi_attributes.length();i++)
            {
                int j=i+1;
                System.out.println("Table "+(j)+": "+Key+multi_attributes.charAt(i));
            }
            return false;
        }
        else
            return true;

    }

    public static boolean convto2NF(String attributes, String Key, Scanner sc, int no_of_fds, String fds[][])
    {
        if(Key.length()==1)
            return true;

        boolean[] vis = new boolean[26];

        for(int i=0;i<26;i++)
           vis[i]=false;

        int no_of_subsets=(Key.length()*(Key.length()+1))/2;

        //String[][] fds = new String[no_of_fds][2];
        String[] subsets=new String[no_of_subsets];
        int subset_count=0;

        for(int len=1;len<=Key.length(); len++)
        {
            for(int i=0;i<=Key.length()-len;i++)
            {

                int j = i + len - 1;
                String curr="";

                for(int k=i;k<=j;k++)
                    curr+=Key.charAt(k);

                subsets[subset_count++]=curr;
            }
        }

        boolean check=true;
        String full_check="";
        int table_no=0;

        for(int i=0;i<no_of_subsets-1;i++)
        {
            for(int x=0;x<no_of_fds;x++)
                if(subsets[i].contains(fds[x][0]))
                {
                    if(check)
                        System.out.println("Relation in 1NF form convered to 2NF:");

                    check=false;
                    String ans="";

                    for(int m=0;m<fds[x][1].length();m++)
                        if(!vis[(fds[x][1].charAt(m))-'a'])
                        {
                            ans+=fds[x][1].charAt(m)+" ";
                            vis[(fds[x][1].charAt(m))-'a']=true;
                        }

                    if(ans.length()!=0){
                        System.out.print("Table:"+(++table_no)+" ");
                        for(int h=0;h<subsets[i].length();h++)
                        {
                            System.out.print(subsets[i].charAt(h)+" ");
                        }
                        System.out.println(ans);
                    }
                    

                    for(int m=0;m<26;m++)
                        if(vis[m])
                           full_check+=(char)('a'+m);

                    full_check  = sortString(full_check);
                    if(full_check.equals(attributes))
                        break;
                }
            if(full_check.equals(attributes))
                    break;
        }
        if(!check)
        {
            String temp="";
            for(int i=0;i<attributes.length();i++)
                if(vis[attributes.charAt(i)-'a']==false)
                {
                    temp+=attributes.charAt(i)+" ";
                }
            if(temp.length()!=0)
            System.out.println("Table "+(++table_no)+": "+temp);

        }

        return check;
    }

//    public static String[][] minimalcover(String F[][], int len)
//    {
//        int new_no_of_fds=0;
//        for(int i=0;i<len;i++)
//            for(int j=0;j<F[i][1].length();j++)
//                    new_no_of_fds++;
//        
//        String[][] new_fds= new String[new_no_of_fds][2];
//        
//        int cnt=0;
//        for(int i=0;i<len;i++)
//            for(int k=0;k<F[i][1].length();k++)
//            {
//                new_fds[cnt][1]="";
//                new_fds[cnt][1]+=F[i][1].charAt(k);
//                new_fds[cnt++][0]=F[i][0];
//            }
//                
//        len=new_no_of_fds;
//        for(int i=0;i<len;i++)
//        {
//            for(int j=0;j<new_fds[i][0].length();j++)
//            {
//                String argument="";
//                for(int k=0;k<new_fds[i][0].length();k++)
//                    if(new_fds[i][0].charAt(k)!=new_fds[i][0].charAt(j))
//                        argument+=new_fds[i][0].charAt(k);
//                
//                if(closure(argument,len,new_fds).contains(F[i][1]))
//                    new_fds[i][0]=argument;
//            }
//        }
//        return new_fds;
//    }
    
    public static boolean convto3NF( String attributes, String fds[][], int no_of_fds,String Key )
    {

        boolean[] vis = new boolean[26];
        boolean check=true;

        for(int i=0;i<26;i++)
           vis[i]=false;

        int t_no=0;
        for(int i=0;i<no_of_fds;i++)
        {
            String newTable="";
                if(!fds[i][0].equals(Key) && !Key.contains(fds[i][1]))
                {
                    if(check)
                    System.out.println("Relation in 2NF form convered to 3NF:");

                    check=false;
                    for(int k=0;k<fds[i][0].length();k++){
                        newTable+=fds[i][0].charAt(k)+" ";
                    }

                    for(int k=0;k<fds[i][1].length();k++)
                    {
                        String key_check = "";
                        key_check+=fds[i][1].charAt(k);
                        if(vis[fds[i][1].charAt(k)-'a']==false && !key_check.equals(Key))
                            {
                                 newTable+=fds[i][1].charAt(k)+" ";
                                 vis[fds[i][1].charAt(k)-'a']=true;
                            }
                    }
                    System.out.println("Table "+(++t_no)+": "+newTable);
                }
        }

        if(!check)
        {
            String temp="";
            for(int i=0;i<attributes.length();i++)
                if(vis[attributes.charAt(i)-'a']==false)
                {
                    temp+=attributes.charAt(i)+" ";
                }
            if(temp.length()!=0)
            System.out.println("Table "+(++t_no)+": "+temp);
        }

        return check;
    }

    public static boolean check1=true;

    public static boolean convtoBCNF( String attributes, String fds[][], int no_of_fds,String Key)
    {
        boolean check=true;

        for(int i=0;i<no_of_fds;i++)
        {
            if(fds[i][0].equals(Key)==false && attributes.contains(fds[i][0]) && attributes.contains(fds[i][1]) )
            {

                if(check1)
                {
                    System.out.println("table in 3NF converted to BCNF: ");
                    check1=false;
                }
                check=false;
                String tab1="";

                for(int j=0;j<fds[i][0].length();j++)
                {
                    tab1+=fds[i][0].charAt(j)+" ";
                    BCNFvis[fds[i][0].charAt(j)-'a']=true;
                }

                for(int j=0;j<fds[i][1].length();j++)
                {
                    if(!BCNFvis[fds[i][1].charAt(j)-'a'])
                        tab1+=fds[i][1].charAt(j)+" ";

                    BCNFvis[fds[i][1].charAt(j)-'a']=true;
                }

                System.out.println("Table: "+tab1);

                String tab2="";

                String beta=sortString(fds[i][1]);
                String alpha=sortString(fds[i][0]);
                String diff="";

                for(int j=0;j<beta.length();j++)
                {
                    String X="";
                    X+=beta.charAt(j);

                    if(alpha.contains(X))
                        continue;
                    else
                        diff+=beta.charAt(j);
                }

                for(int j=0;j<attributes.length();j++)
                {
                    String X="";
                    X+=attributes.charAt(j);

                    if(diff.contains(X))
                        continue;
                    else
                    {
                        tab2+=attributes.charAt(j)+" ";
                        BCNFvis[attributes.charAt(j)-'a']=true;
                    }
                }

//               fds[i][0]="##";
                System.out.println("Table: "+tab2);


               tab1 = tab1.replace(" ","");
               tab1=sortString(tab1);
               String newKey=key(tab1,fds,no_of_fds);
               convtoBCNF(tab1,fds,no_of_fds,newKey);

               tab2 = tab2.replace(" ","");
               tab2=sortString(tab2);
               newKey=key(tab2,fds,no_of_fds);
               convtoBCNF(tab2,fds,no_of_fds,newKey);

            }
        }

        return check;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String attributes = sc.nextLine();
        attributes=sortString(attributes);
        int no_of_fds = sc.nextInt();
        sc.nextLine();
        String[][] fds = new String[no_of_fds][2];

        for(int i=0;i<no_of_fds;i++)
        {
            String curr=sc.nextLine();
            fds[i]=curr.split(" ",2);
            fds[i][0]=sortString(fds[i][0]);
            fds[i][1]=sortString(fds[i][1]);
        }

//        System.out.println(closure(fds[0][0],no_of_fds,fds));

        String Key=key(attributes,fds,no_of_fds);
        System.out.println("KEY: "+Key);

        for(int i=0;i<26;i++)
           BCNFvis[i]=false;


//        static boolean[] FDvis = new boolean[no_of_fds];
//
//        for(int i=0;i<no_of_fds;i++)
//           FDvis[i]=false;

        if(convto1NF(attributes,Key,sc))
            if(convto2NF(attributes,Key,sc,no_of_fds,fds))
                if(convto3NF(attributes,fds,no_of_fds,Key))
                {
                    if(convtoBCNF(attributes,fds,no_of_fds,Key))
                        System.out.println("Table already in BCNF form");
                    else
                    {
                        String fin="";
                        boolean check=false;
                        for(int i=0;i<attributes.length();i++)
                        {
                            if(BCNFvis[i]==false)
                            {
                                fin+=attributes.charAt(i)+" ";
                                BCNFvis[i]=true;
                                check=true;
                            }
                        }
                        if(check==true)
                         System.out.println("Table: "+fin);
                    }
                }

    }

}
