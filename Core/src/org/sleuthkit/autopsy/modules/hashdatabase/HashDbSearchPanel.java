/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.modules.hashdatabase;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.coreutils.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import org.sleuthkit.autopsy.ingest.IngestManager;

/**
 * Searches for files by md5 hash, based off the hash given in this panel.
 */
class HashDbSearchPanel extends javax.swing.JPanel implements ActionListener {

    private static final Logger logger = Logger.getLogger(HashDbSearchPanel.class.getName());
    private static HashDbSearchPanel instance;

    /**
     * @return the default instance of this panel
     */
    public static HashDbSearchPanel getDefault() {
        if (instance == null) {
            instance = new HashDbSearchPanel();
        }
        return instance;
    }

    /**
     * Check if ingest is currently running and refresh the panel accordingly.
     */
    public void refresh() {
        boolean running = IngestManager.getInstance().isIngestRunning();
        if (running) {
            titleLabel.setForeground(Color.red);
            titleLabel.setText(NbBundle.getMessage(this.getClass(), "HashDbSearchPanel.titleText.ingestOngoing"));
        } else {
            titleLabel.setForeground(Color.black);
            titleLabel.setText(NbBundle.getMessage(this.getClass(), "HashDbSearchPanel.titleLabel.text"));
        }
        hashField.setEditable(!running);
        searchButton.setEnabled(!running);
        addButton.setEnabled(!running);
        removeButton.setEnabled(!running);
        hashTable.setEnabled(!running);
        hashLabel.setEnabled(!running);
        saveBox.setEnabled(!running);
    }

    /**
     * Creates new form HashDbSearchPanel
     */
    private HashDbSearchPanel() {
        setName(HashDbPanelSearchAction.ACTION_NAME);
        initComponents();
        customInit();
    }

    final void customInit() {
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        errorField.setVisible(false);
        hashField.requestFocus();
        // Don't let the user input more characters than in an MD5 hash
        hashField.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                if ((this.getLength() + str.length()) <= 32) {
                    super.insertString(offset, str, a);
                }
            }
        });
        // Pressing enter adds the hash
        hashField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    addButton.doClick();
                }
            }
        });
        // Pressing delete removes the selected rows
        hashTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_DELETE) {
                    removeButton.doClick();
                }
            }
        });
    }

    void addSearchActionListener(ActionListener l) {
        for (ActionListener al : searchButton.getActionListeners()) {
            searchButton.removeActionListener(al);
        }
        searchButton.addActionListener(l);
    }

    void addCancelActionListener(ActionListener l) {
        cancelButton.addActionListener(l);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("rawtypes")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        hashTable = new javax.swing.JTable();
        hashField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        hashLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();
        errorField = new javax.swing.JLabel();
        saveBox = new javax.swing.JCheckBox();
        cancelButton = new javax.swing.JButton();

        hashTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MD5 Hashes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(hashTable);
        if (hashTable.getColumnModel().getColumnCount() > 0) {
            hashTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.hashTable.columnModel.title0")); // NOI18N
        }

        hashField.setText(org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.hashField.text")); // NOI18N

        addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.addButton.text")); // NOI18N

        hashLabel.setFont(hashLabel.getFont().deriveFont(hashLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(hashLabel, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.hashLabel.text")); // NOI18N

        searchButton.setFont(searchButton.getFont().deriveFont(searchButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(searchButton, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.searchButton.text")); // NOI18N

        removeButton.setFont(removeButton.getFont().deriveFont(removeButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(removeButton, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.removeButton.text")); // NOI18N

        titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.titleLabel.text")); // NOI18N

        errorField.setFont(errorField.getFont().deriveFont(errorField.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        errorField.setForeground(new java.awt.Color(255, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(errorField, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.errorField.text")); // NOI18N

        saveBox.setFont(saveBox.getFont().deriveFont(saveBox.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(saveBox, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.saveBox.text")); // NOI18N
        saveBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBoxActionPerformed(evt);
            }
        });

        cancelButton.setFont(cancelButton.getFont().deriveFont(cancelButton.getFont().getStyle() & ~java.awt.Font.BOLD, 11));
        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(HashDbSearchPanel.class, "HashDbSearchPanel.cancelButton.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hashLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hashField))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(addButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveBox)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hashLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hashField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(removeButton)
                    .addComponent(saveBox))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(errorField)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorField;
    private javax.swing.JTextField hashField;
    private javax.swing.JLabel hashLabel;
    private javax.swing.JTable hashTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton removeButton;
    private javax.swing.JCheckBox saveBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addButton)) {
            add();
        } else if (e.getSource().equals(removeButton)) {
            remove();
        }
    }

    /**
     * Search through all tsk_files to find ones with the same hashes as the
     * hashes given.
     */
    boolean search() {
        // Check if any hashed have been entered
        if (hashTable.getRowCount() != 0) {
            // Make sure at least 1 file has an md5 hash
            if (HashDbSearcher.countFilesMd5Hashed() > 0) {
                return doSearch();
            } else {
                JOptionPane.showMessageDialog(null,
                        NbBundle.getMessage(this.getClass(),
                                "HashDbSearchPanel.noFilesHaveMD5HashMsg"),
                        NbBundle.getMessage(this.getClass(), "HashDbSearchPanel.dlgMsg.title"),
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            errorField.setText(NbBundle.getMessage(this.getClass(), "HashDbSearchPanel.errorText.noHashesAddedMsg"));
            errorField.setVisible(true);
            return false;
        }
    }

    private boolean doSearch() {
        errorField.setVisible(false);
        // Get all the rows in the table
        int numRows = hashTable.getRowCount();
        ArrayList<String> hashes = new ArrayList<String>();
        for (int i = 0; i < numRows; i++) {
            hashes.add((String) hashTable.getValueAt(i, 0));
        }
        // Start a new thread and find the hashes
        HashDbSearchThread hashThread = new HashDbSearchThread(hashes);
        hashThread.execute();
        return true;
    }

    /**
     * Add the given text into the table of hashes.
     */
    void add() {
        errorField.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) hashTable.getModel();
        String hash = hashField.getText();
        if (!hash.equals("")) {
            if (hash.matches("[a-fA-F0-9]{32}")) {
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(hashField.getText())) {
                        hashField.setText("");
                        errorField.setText(
                                NbBundle.getMessage(this.getClass(), "HashDbSearchPanel.errorText.hashAlreadyAddedMsg"));
                        errorField.setVisible(true);
                        errorField.setVisible(true);
                        return;
                    }
                }
                model.addRow(new String[]{hash});
                hashField.setText(""); // wipe the field
            } else {
                errorField.setText(NbBundle.getMessage(this.getClass(), "HashDbSearchPanel.errorText.invalidMD5HashMsg"));
                errorField.setVisible(true);
            }
        }
        hashField.requestFocus(); // select the field to type in
    }

    /**
     * Remove all of the highlighted/selected rows from the table of hashes.
     */
    void remove() {
        DefaultTableModel model = (DefaultTableModel) hashTable.getModel();
        int rows[] = hashTable.getSelectedRows();
        // Loop backwards to delete highest row index first, otherwise
        // index numbers change and the wrong rows are deleted
        for (int i = rows.length - 1; i >= 0; i--) {
            model.removeRow(rows[i]);
        }
    }

    /**
     * Clears the table of hashes
     */
    void clear() {
        if (!saveBox.isSelected()) {
            DefaultTableModel model = (DefaultTableModel) hashTable.getModel();
            int numRows = hashTable.getRowCount();
            for (int i = numRows - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
        errorField.setVisible(false);
        hashField.setText("");
    }
}
