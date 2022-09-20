//Shadan Khan/1876267/SE/FinalProject
package bookbank;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class BookBank extends javax.swing.JFrame {

    static ArrayList<Borrower> borrowers = new ArrayList<>();

    //########################Add Book################################
    private static void AddBook(Scanner input, PrintWriter output, ArrayList<Materials> materials) {

        Book b = new Book(input.next(), input.next(), input.next(), input.nextInt(), input.nextInt(), input.nextInt());
        materials.add(b);
        output.println("***************************************");
        output.println("COMMAND: Add_Book");
        // output.println("***************************************");
        output.println("Success: " + "\r\n" + b);
    }

    //########################Add Tape################################
    private static void AddTape(Scanner input, PrintWriter output, ArrayList<Materials> materials) {
        String title = input.next();
        int year = input.nextInt();

        Tapes t = new Tapes(title, year);
        materials.add(t);
        output.println("***************************************");
        output.println("COMMAND: Add_Tape");
        //output.println("***************************************");
        output.println("Success: " + t + "\n");
    }

    //########################Add Article################################
    private static void addArticle(Scanner input, PrintWriter output, ArrayList<Materials> materials) {
        String title = input.next();
        String author = input.next();
        int volume = input.nextInt();
        String pages = input.next();
        String journalName = input.next();

        Article j = new Article(title, author, volume, pages, journalName);
        materials.add(j);
        output.println("***************************************");
        output.println("COMMAND: Add_Article");
        //output.println("***************************************");
        output.println("Success: " + j + "\n");

    }

    //########################Add Student################################
    private static void addStudent(Scanner input, PrintWriter output, ArrayList<Materials> materials) {
        output.println("***************************************");
        output.println("COMMAND: Add_borrower_Student");
        int ID = input.nextInt();
        String name = input.next();
        char gender = input.next().charAt(0);
        int phone = input.nextInt();
        String major = input.next();
        String itemName = input.next();
        int borrowDuration = input.nextInt();
        boolean found = false;
        Materials material = null;

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Book) {
                if (((Book) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;
                    if (((Book) materials.get(i)).isAvailable() == true) {
                        material = materials.get(i);
                        ((Book) material).borrow();

                        output.println(itemName + " book is found!" + " The remaining number of Copies " + ((Book) materials.get(i)).getNoOfCopies());
                    }
                }
            } else if (materials.get(i) instanceof Tapes) {
                if (((Tapes) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;

                    if (((Tapes) materials.get(i)).isAvailable() != false) {
                        material = materials.get(i);
                        ((Tapes) material).borrow();

                        output.println(itemName + " tape is found!");
                    }
                }
            } else if (materials.get(i) instanceof Article) {
                if (((Article) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;

                    if (((Article) materials.get(i)).isAvailable() != false) {
                        material = materials.get(i);
                        ((Article) material).borrow();

                        output.println(itemName + " Article is found!");
                    }
                }
            }

        }
        //material not found!!error
        if (material == null) {
            if (found == true) {
                output.println("Error: " + itemName + " is Not avaliable!\n");
            } else {
                try {
                    throw new MaterialNotFoundException(itemName);
                } catch (MaterialNotFoundException ex) {
                    Logger.getLogger(BookBank.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } //add new student
        else {
            Student student = new Student(ID, name, gender, phone, major, material, borrowDuration);
            material.addNewBorrower(student);
            borrowers.add(student);
            output.println("Success: " + student.BorrowerInfo(itemName));
        }

    }
    //########################Add Staff################################

    private static void addStaff(Scanner input, PrintWriter output, ArrayList<Materials> materials) {
        output.println("***************************************");
        output.println("COMMAND: Add_Borrower_Staff");

        // output.println("***************************************");
        int ID = input.nextInt();
        String name = input.next();
        char gender = input.next().charAt(0);
        int phone = input.nextInt();
        String job = input.next();
        String itemName = input.next();
        int borrowDuration = input.nextInt();
        boolean found = false;
        Materials material = null;

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Book) {
                if (((Book) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;
                    if (((Book) materials.get(i)).isAvailable() == true) {
                        material = materials.get(i);
                        ((Book) material).borrow();

                        output.println(itemName + " book is found!" + " The remaining number of Copies " + ((Book) materials.get(i)).getNoOfCopies());
                    }
                }
            } else if (materials.get(i) instanceof Tapes) {
                if (((Tapes) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;

                    if (((Tapes) materials.get(i)).isAvailable() != false) {
                        material = materials.get(i);
                        ((Tapes) material).borrow();

                        output.println(itemName + " tape is found!");
                    }
                }
            } else if (materials.get(i) instanceof Article) {
                if (((Article) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;

                    if (((Article) materials.get(i)).isAvailable() != false) {
                        material = materials.get(i);
                        ((Article) material).borrow();

                        output.println(itemName + " Article is found!");
                    }
                }
            }

        }
        if (material == null) {
            if (found == true) {
                output.println("Error: " + itemName + " is Not avaliable!\n");
            } else {
                try {
                    throw new MaterialNotFoundException(itemName);
                } catch (MaterialNotFoundException ex) {
                    Logger.getLogger(BookBank.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            Staff staff = new Staff(ID, name, gender, phone, job, material, borrowDuration);
            material.addNewBorrower(staff);
            borrowers.add(staff);
            output.println("Success: " + staff.BorrowerInfo(itemName));
        }

    }
    //######################## check Status ################################

    private static void checkStatus(Scanner input, PrintWriter output, ArrayList<Materials> materials) throws MaterialNotFoundException {
        output.println("***************************************");
        output.println("COMMAND: Check_Item_Status");
        // output.println("***************************************");

        String itemName = input.next();
        Materials material = null;
        boolean found = false;

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Book) {
                if (((Book) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;
                    if (materials.get(i).isAvailable()) {

                        output.println("Success: " + itemName + " book is found!" + " The remaining number of Copies " + ((Book) materials.get(i)).getNoOfCopies());
                        output.print("This item ");
                        materials.get(i).getItemOwners(output);
                        output.println();
                    } else {

                        output.println("Error: " + itemName + " book is Not found!" + " No more availabe Copies!");
                        output.print("This item ");
                        materials.get(i).getItemOwners(output);
                        output.println();
                    }
                }
            } else if (materials.get(i) instanceof Tapes) {
                if (((Tapes) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;
                    if (materials.get(i).isAvailable()) {

                        output.println("Success: " + itemName + " Tape is found!");

                    } else {

                        output.println("Error: " + itemName + " book is Borrowed!");
                        output.print("This item ");
                        materials.get(i).getItemOwners(output);
                        output.println();
                    }
                }
            } else if (materials.get(i) instanceof Article) {
                if (((Article) materials.get(i)).getTitle().equals(itemName)) {
                    found = true;
                    if (materials.get(i).isAvailable()) {

                        output.println("Success: " + itemName + " Article is found!");

                    } else {

                        output.println("Error: " + itemName + " Article is Borrowed!");
                        output.print("This item ");
                        materials.get(i).getItemOwners(output);
                        output.println();
                    }
                }

            }
        }
        output.println();

        if (found != true) {

            throw new MaterialNotFoundException(itemName);

        }

    }

    //######################## Display Books  ################################
    private static void displayBooks(PrintWriter outputReport, ArrayList<Materials> materials) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Available_Books");
        //outputReport.println("***************************************");
        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Book) {
                if (((Book) materials.get(i)).isAvailable()) {
                    outputReport.println(((Book) materials.get(i)) + "\n");
                    outputReport.println("-------------------");
                }
            }
        }
    }
    //######################## Display Tapes  ################################

    private static void displayTapes(PrintWriter outputReport, ArrayList<Materials> materials) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Available_Tapes");

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Tapes) {
                if (((Tapes) materials.get(i)).isAvailable()) {

                    outputReport.println(((Tapes) materials.get(i)) + "\n");
                    outputReport.println("-------------------");
                }
            }
        }
    }
//########################Diplay Articles################################

    private static void displayArticles(PrintWriter outputReport, ArrayList<Materials> materials) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Available_Article");

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Article) {
                if (((Article) materials.get(i)).isAvailable()) {

                    outputReport.println(((Article) materials.get(i)) + "\n");
                    outputReport.println("-------------------");
                }
            }
        }
    }
//########################Diplay Borrowed Books################################

    private static void displayBorrowedBooks(PrintWriter outputReport, ArrayList<Materials> materials) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Borrowed_Books:");

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Book) {
                if (((Book) materials.get(i)).isAvailable() == false) {
                    outputReport.print("Book \" " + materials.get(i).getTitle() + " \" ");
                    materials.get(i).getItemOwners(outputReport);
                    outputReport.println();
                }
            }
        }
    }
//########################Diplay Borrowed Tapes################################

    private static void displayBorrowedTapes(PrintWriter outputReport, ArrayList<Materials> materials) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Borrowed_Tapes:");

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Tapes) {
                if (((Tapes) materials.get(i)).isAvailable() == false) {
                    outputReport.print("Tape \" " + materials.get(i).getTitle() + " \" ");
                    materials.get(i).getItemOwners(outputReport);
                    outputReport.println();
                }
            }
        }
    }
    //######################## Display Borrowed Article  ################################

    private static void displayBorrowedArticle(PrintWriter outputReport, ArrayList<Materials> materials) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Borrowed_Article:");

        for (int i = 0; i < materials.size(); i++) {
            if (materials.get(i) instanceof Article) {
                if (((Article) materials.get(i)).isAvailable() == false) {
                    outputReport.print("Article \" " + materials.get(i).getTitle() + " \" ");
                    materials.get(i).getItemOwners(outputReport);
                    outputReport.println();
                }
            }
        }
    }
    //######################## Display Fees  ################################

    private static void displayFees(PrintWriter outputReport) {
        outputReport.println("***************************************");
        outputReport.println("COMMAND: Display_Fees:");

        for (int i = 0; i < borrowers.size(); i++) {
            if (borrowers.get(i).getFees() > 0) {
                outputReport.println("Total fees for " + (borrowers.get(i)).getName() + " should pay " + borrowers.get(i).getFees());
            } else {
                outputReport.println("the borrower " + borrowers.get(i).getName() + " dose not have any fees to pay.");
            }
        }
    }
    //######################## Return Item  ################################

    private static void ReturnItem(Scanner input, PrintWriter output, ArrayList<Materials> materials) {
        output.println("***************************************");
        output.println("COMMAND: Return_Item:");

        int ID = input.nextInt();
        String ItemName = input.next();
        int borrowDuration = input.nextInt();
        //outer loop
        for (int i = 0; i < materials.size(); i++) {
            //check Article
            if (materials.get(i) instanceof Article) {
                if (materials.get(i).getTitle().equals(ItemName)) {
                    materials.get(i).ReturnItem(output, ID, borrowDuration);
                }
            }
            //check Book
            if (materials.get(i) instanceof Book) {
                if (materials.get(i).getTitle().equals(ItemName)) {
                    materials.get(i).ReturnItem(output, ID, borrowDuration);
                }
            }
            //check Tapes
            if (materials.get(i) instanceof Tapes) {
                if (materials.get(i).getTitle().equals(ItemName)) {
                    materials.get(i).ReturnItem(output, ID, borrowDuration);
                }
            }
        }

    }

    /**
     * Creates new form MainForm
     */
    public BookBank() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTextID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbSearch = new javax.swing.JButton();
        jbExit = new javax.swing.JButton();
        jbSearch1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jTextName = new javax.swing.JTextField();
        jTextFee = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Book Bank Management System");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 255));
        jLabel1.setText("Welcome to Book Bank System");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Borrower Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 51, 51))); // NOI18N

        jTextID.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 51));
        jLabel6.setText("Borrower ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38)
                        .addComponent(jTextID, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jTextID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(0, 51, 51))); // NOI18N

        jbSearch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbSearch.setText("Search");
        jbSearch.setToolTipText("Click to search record from the file!");
        jbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSearchActionPerformed(evt);
            }
        });

        jbExit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbExit.setText("Exit");
        jbExit.setToolTipText("Click to Exit from the program!");
        jbExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExitActionPerformed(evt);
            }
        });

        jbSearch1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbSearch1.setText("Clear");
        jbSearch1.setToolTipText("Click to search record from the file!");
        jbSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSearch1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jbExit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jbSearch)
                                .addComponent(jbExit)
                                .addComponent(jbSearch1))
                        .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fee Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 51));
        jLabel7.setText("Borrower Name");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 51));
        jLabel8.setText("Requiered Fee");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFee, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(138, 138, 138)
                                        .addComponent(jLabel1)))
                        .addGap(0, 44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Item Search");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExitActionPerformed
        if (evt.getSource() == jbExit) {
            System.exit(0);
        }
    }//GEN-LAST:event_jbExitActionPerformed

    private void jbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSearchActionPerformed
        String id = jTextID.getText();
        int ID = Integer.parseInt(id);
        
        boolean BNF = false;
        if (evt.getSource() == jbSearch) {
            if (borrowers.size() > 0) {

                for (int i = 0; i < borrowers.size(); i++) {
                    if (ID == borrowers.get(i).getId()) {
                        String name = borrowers.get(i).getName();
                        double Fee = borrowers.get(i).getFees();
                        jTextFee.setText(Fee + "");
                        jTextName.setText(name);
                        BNF = true;
                    }

                }
                if (BNF == false) {
                    JOptionPane.showMessageDialog(null, "Borrower Not Found!", "Warning!", JOptionPane.ERROR_MESSAGE);
                    jTextName.setText(null);
                    jTextFee.setText(null);
                }
            }

        }//GEN-LAST:event_jbSearchActionPerformed

    }

    private void jbSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSearch1ActionPerformed
        if (evt.getSource() == jbSearch1) {
            jTextID.setText(null);
            jTextName.setText(null);
            jTextFee.setText(null);
        }
    }//GEN-LAST:event_jbSearch1ActionPerformed
//______________________________________________________
    //##################Main Method############################
//______________________________________________________

    public static void main(String args[]) throws Exception {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>
        // OPEN FILES
        // Input File:
        File inputFile = new File("in.txt");// reading data 
        if (!inputFile.exists()) {
            System.out.println("Input file, " + inputFile + ", does not exist.");
            System.exit(0);
        }
        File outputFile = new File("output.txt");// file pointer to Write file
        PrintWriter output = new PrintWriter(outputFile); // creating Object to write data in file

        File outputFileReport = new File("report.txt");// file pointer to Write file
        PrintWriter outputReport = new PrintWriter(outputFileReport); // creating Object to write data in file
        // Make Scanner for input 
        Scanner input = new Scanner(inputFile);
        String command;
//creating the arrays
        ArrayList<Materials> materials = new ArrayList<>();

        output.println("*** Welcome to Book Bank System ***\r\n");

        do {

            command = input.next();
            try {

                if (command.equalsIgnoreCase("Add_Book")) {

                    AddBook(input, output, materials);

                } else if (command.equalsIgnoreCase("Add_Tape")) {
                    AddTape(input, output, materials);

                } else if (command.equalsIgnoreCase("Add_Article")) {
                    addArticle(input, output, materials);

                } else if (command.equalsIgnoreCase("Add_borrower_Student")) {
                    addStudent(input, output, materials);

                } else if (command.equalsIgnoreCase("Add_Borrower_Staff_member")) {
                    addStaff(input, output, materials);

                } else if (command.equalsIgnoreCase("Check_Item_Status")) {
                    checkStatus(input, output, materials);

                } else if (command.equalsIgnoreCase("Display_Available_Books")) {
                    Collections.sort(materials);
                    displayBooks(outputReport, materials);

                } else if (command.equalsIgnoreCase("Display_Available_Tapes")) {
                    displayTapes(outputReport, materials);

                } else if (command.equalsIgnoreCase("Display_Available_Article")) {
                    displayArticles(outputReport, materials);

                } else if (command.equalsIgnoreCase("Display_Borrowed_Books")) {
                    displayBorrowedBooks(outputReport, materials);

                } else if (command.equalsIgnoreCase("Display_Borrowed_Tapes")) {
                    displayBorrowedTapes(outputReport, materials);

                } else if (command.equalsIgnoreCase("Display_Borrowed_Article")) {
                    displayBorrowedArticle(outputReport, materials);

                } else if (command.equalsIgnoreCase("Display_Fees")) {
                    displayFees(outputReport);

                } else if (command.equalsIgnoreCase("Return_Item")) {
                    ReturnItem(input, output, materials);
                }

            } catch (Exception ex) {
                output.println(ex);
            }
        } while (!command.equalsIgnoreCase("Quit"));//end while

        input.close();
        output.close();
        outputReport.close();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookBank().setVisible(true);
            }
        });
    }//end main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFee;
    private javax.swing.JTextField jTextID;
    private javax.swing.JTextField jTextName;
    private javax.swing.JButton jbExit;
    private javax.swing.JButton jbSearch;
    private javax.swing.JButton jbSearch1;
    // End of variables declaration//GEN-END:variables
}

    //DON'T MAKE ANY CHANGE IN THIS FILE.
class Article extends Materials {

    private String author;
    private int volume;
    private String journalName;
    private String pages;

    // Book full-arg constructor
    Article(String title, String author, int volume, String pages, String journalName) {
        this.title = title;
        this.author = author;
        this.volume = volume;
        this.pages = pages;
        this.journalName = journalName;
    }

    // Getters
    public String getAuthor() {
        return author;
    }

    public int getVolume() {
        return volume;
    }

    public String getJournalName() {
        return journalName;
    }

    public String getPages() {
        return pages;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public String toString() {
        return "Title: " + getTitle() + "\r\nAuthor: " + getAuthor() + " Journal Name: " + getJournalName() + "\r\nPages: " + getPages() + " Volume " + getVolume();
    }

}//END CLASS ARTICLE
//DON'T MAKE ANY CHANGE IN THIS FILE.

class Book extends Materials {

    // Book private data fields
    private String isbn;
    private String author;
    private int version;
    private int year;
    private int noOfCopies;

    // Book full-arg constructor
    Book(String isbn, String title, String author, int version, int noOfCopies, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.version = version;
        this.noOfCopies = noOfCopies;
        this.year = year;
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public int getVersion() {
        return version;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void increaseNoOfCopies() {
        this.noOfCopies++;
    }

    public boolean isAvailable() {
        if (noOfCopies > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void borrow() {
        noOfCopies--;
        if (noOfCopies > 0) {
            available = true;
        } else {
            available = false;
        }
    }

    public String toString() {
        return "Isbn: " + getIsbn() + " Title: " + getTitle() + "\r\n" + "Author: " + getAuthor() + " Version: " + getVersion() + " Number of available Copies: " + getNoOfCopies() + " Issue year: " + getYear();
    }
}//END CLASS BOOK

 // Change as per the description in the given specification file and comments
abstract class Borrower implements Payable {

    protected int ID;
    protected String name;
    protected char gender;
    protected int phone;
    protected int borrowDuration;
    protected int ActualBorrowDuration = 0;

    protected ArrayList<Materials> borrowedItems = new ArrayList<>();

    Borrower(int ID, String name, char gender, int phone, Materials material, int borrowDuration) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.borrowDuration = borrowDuration;
        this.borrowedItems.add(material);
    }

    public void setId(int id) {
        this.ID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setBorrowDuration(int borrowDuration) {
        this.borrowDuration = borrowDuration;
    }

    public void setActualBorrowDuration(int ActualBorrowDuration) {
        this.ActualBorrowDuration = ActualBorrowDuration;
    }

    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public int getPhone() {
        return phone;
    }

    public int getBorrowDuration() {
        return borrowDuration;
    }

    public String BorrowerInfo(String title) {
        return "Name: " + getName() + " with id " + getId() + " borrowed " + title + "\n";
    }

    public int getActualBorrowDuration() {
        return ActualBorrowDuration;
    }

}
//-----------------------------------------------------

class Student extends Borrower {

    private String major;

    Student(int ID, String name, char gender, int phone, String major, Materials material, int borrowDuration) {
        super(ID, name, gender, phone, material, borrowDuration);
        setMajor(major);

    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public double getFees() {
        double fee = 0;
        int numberOfLateDays;
        if (getActualBorrowDuration() > getBorrowDuration()) {
            numberOfLateDays = getActualBorrowDuration() - getBorrowDuration();
            fee = numberOfLateDays * 5.0;
        }
        return fee;
    }

}

//---------------------------------------------------------
class Staff extends Borrower {

    private String job;

    Staff(int ID, String name, char gender, int phone, String job, Materials material, int borrowDuration) {
        super(ID, name, gender, phone, material, borrowDuration);
        setJob(job);

    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public double getFees() {
        double fee = 0;
        int numberOfLateDays;
        if (getActualBorrowDuration() > getBorrowDuration()) {
            numberOfLateDays = getActualBorrowDuration() - getBorrowDuration();
            fee = numberOfLateDays * 7.0;
        }
        return fee;
    }

}//END CLASSES OF BORROWER
//DON'T MAKE ANY CHANGE IN THIS FILE.

abstract class Materials implements Comparable<Materials> {

    protected ArrayList<Borrower> borrowers = new ArrayList<>();
    protected boolean available = true;
    protected String title;

    public String getTitle() {
        return title;
    }

    public void addNewBorrower(Borrower b) {
        borrowers.add(b);
    }

    public abstract boolean isAvailable();

    public abstract void borrow();

    public void getItemOwners(PrintWriter output) {
        if (borrowers.size() > 0) {
            for (int i = 0; i < borrowers.size(); i++) {
                if (i == 0) {
                    output.print(" was borrowed by " + borrowers.get(i).getName());
                }
                if (borrowers.size() > 1 && i > 0) {
                    output.print(", " + borrowers.get(i).getName());
                }
            }
        } else {
            output.println("** No one Ownes this item!\n");
        }
    }

    public void ReturnItem(PrintWriter output, int ID, int actualBorrowDuration) {
        this.available = true;
        if (borrowers.size() > 0) {
            for (int i = 0; i < borrowers.size(); i++) {
                if (borrowers.get(i).getId() == ID) {

                    borrowers.get(i).setActualBorrowDuration(actualBorrowDuration);
                    output.println(borrowers.get(i).getName() + " returned " + getTitle());
                }
            }
        }
        output.println();
    }

    public int compareTo(Materials o) {

        return getTitle().compareTo(o.getTitle());

    }

}//END CLASS MATERIALS

interface Payable {

    public abstract double getFees();
}//END INTERFACE PAYABLE
//DON'T MAKE ANY CHANGE IN THIS FILE.

class Tapes extends Materials {

    private int year;

    // Book full-arg constructor
    Tapes(String title, int year) {
        this.title = title;
        this.year = year;
    }

    private int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public String toString() {
        return " Title: " + getTitle() + " Year: " + getYear();
    }

}//END CLASS TAPES

class MaterialNotFoundException extends Exception {

    MaterialNotFoundException(String title) {
        System.err.println("Error: The material titled " + title + " was not found in this Book Bank");

    }
}
