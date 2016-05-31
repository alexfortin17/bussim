
package Presentation;
import Application.Application;
import Domaine.Simulation;
import Domaine.uiExcept;
import Fondation.Factory;
import Fondation.SimulExcept;
import Fondation.Utils;
import InfrastructureMetier.AllerSimple;
import InfrastructureMetier.Arret;
import InfrastructureMetier.Circuit;
import InfrastructureMetier.Coordonnee;
import InfrastructureMetier.Intersection;
import InfrastructureMetier.Itineraire;
import InfrastructureMetier.Point;
import InfrastructureMetier.Segment;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import static java.lang.Math.abs;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class InterfacePrincipale extends javax.swing.JFrame implements ActionListener {
    //DECLARATION VARIABLES
    private Dessinateur dessinateur;//Dessinateur
    private Application application = Factory.creerApplication();//Application
    private String nomProjet = "nouveauProjet1";
    private int iterSegment=0;//Itérateur compte le nombre de clic lors création segment
    private int iterReseau=0;//Itérateur compte nombre de clic lors création circuit
    private int iterSimulation=0;//Iterateur permettant d'identifier si une simulation a deja ete demarrée sans être terminée
    private int iterItineraire=0;////Itérateur compte nombre de clic lors création circuit
    private Point tempPoint = null;//Variable temporaire pour stockage d'instance de points
    private Segment tempSegment1 = null;//Variable temporarire pour stockage d'instance de segments
    private ArrayList<Segment> listeTempSegment = new ArrayList();//Variable temporaire pour stockage liste de segments lors clic
    private String nomCircuitModif;//Permet de se rappeler du nom du circuit dont le point source est différent du point de depart
    private boolean modifierSourceCircuit=false;//Boolean indiquant si nous sommes en mode édition point source circuit
    private ArrayList<Point> listePointsTemp = new ArrayList();//ArrayList stockage des points d'un circuit
    private ArrayList<Circuit> listeCircuitsTemp = new ArrayList();//ArrayList stockage des circuits lors création itinéraire 
    private ArrayList<Point> listePivotsTemp = new ArrayList();//ArrayList stockage des points pivots lors création itinéraire 
    private boolean boucle=false;//Valeur definissant circuit boucle ou non
    private double minReseau = 0;//Valeur du min lors création reseau avec point source different point depart
    private double modeReseau = 0;//Valeur du mode lors création reseau avec point source different point depart
    private double maxReseau = 0;//Valeur du max lors création reseau avec point source different point depart
    private int maxBusReseau = 0;//Valeur du nombre max de bus sur circuit lors création reseau avec point source different point depart
    private int lastXLocationClic;//Dernier emplacement du clic de souris (X)
    private int lastYLocationClic;//Dernier emplacement du clic souris (Y)
    private JInternalFrame carte = new JInternalFrame("Carte",true,false,false,false);//La carte 
    private Date date1 = new Date();//Date de début de la simulation
    private Date date2 = new Date();//Date de fin de la simulation
    private Date dateCourante = new Date();//Date courante (de la simulation)
    private long timerAjust = 1000;//Variable ajustement vitesse timer
    private Timer timer;//Le timer
    private boolean iterFinSim=false;//Permet de savoir si la simulation a été arrêtée
    private boolean resetSim = false;//Permet d'identifier si nous devons reinitialiser les simulations
    private final String version = "1.0.0";
    private boolean deplacerPoint = false;//Défini si nous sommes en mode déplacement de points
    private ArrayList<Object> undoObjects = new ArrayList();
    private ArrayList<Object> redoObjects = new ArrayList();
    private String dernierUndoRedo = "";//Permet d'identifier si nous avons effectué un undo suivi d'un redo 
    
            
    public InterfacePrincipale() {
        initComponents();       
        //SplashScreen - Chargement
        try{
            //Temps de chargement
            Thread.sleep(1500);
            //Ajoute l'icone au frame principale de l'application
            URL logo = getClass().getResource("/Presentation/images/logo.png");
            ImageIcon icon = new ImageIcon(logo);
            this.setIconImage(icon.getImage());
            loadDefaultSetup();
              
        }catch (Exception e){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Veuillez contacter votre administrateur \n" + e.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }     
    }
    
    //---------------------------
    //ÉLÉMENTS DU UI - AUTO-GÉNÉRATION
    //----------------------------
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem8 = new javax.swing.JMenuItem();
        jPanelBackMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelPrincipalResizable = new javax.swing.JPanel();
        jPanelButton = new javax.swing.JPanel();
        jLabelX = new javax.swing.JLabel();
        jLabelXCoordonnees = new javax.swing.JLabel();
        jLabelY = new javax.swing.JLabel();
        jLabelYCoordonnees = new javax.swing.JLabel();
        jButtonZoomIn = new javax.swing.JButton();
        jButtonZoomOut = new javax.swing.JButton();
        jLabelNomProjet = new javax.swing.JLabel();
        jLabelZoom = new javax.swing.JLabel();
        jComboBoxTypeCarte = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanelTop = new javax.swing.JPanel();
        jButtonNew = new javax.swing.JButton();
        jButtonOpen = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonUndo = new javax.swing.JButton();
        jButtonRedo = new javax.swing.JButton();
        jComboBoxMode = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanelLeft = new javax.swing.JPanel();
        jTabbedPaneGauche = new javax.swing.JTabbedPane();
        jPanelGrapheContainer = new javax.swing.JPanel();
        jPanelGraphe = new javax.swing.JPanel();
        jPanelBoutonsGrapheTitre = new javax.swing.JPanel();
        jSeparatorTopGraphe = new javax.swing.JSeparator();
        jToggleButtonMinimizeBoutonsGraphe = new javax.swing.JToggleButton();
        jLabelOutilsGraphe = new javax.swing.JLabel();
        jPanelBoutonsGraphe = new javax.swing.JPanel();
        jToggleButtonPoint = new javax.swing.JToggleButton();
        jToggleButtonSegment = new javax.swing.JToggleButton();
        jPanelDetailsSelectionTitre = new javax.swing.JPanel();
        jLabelDetailsSelection = new javax.swing.JLabel();
        jToggleButtonMinimizeDetailsSelection = new javax.swing.JToggleButton();
        jSeparatorDetailsSelection = new javax.swing.JSeparator();
        jPanelDetailsSelection = new javax.swing.JPanel();
        jLabelType = new javax.swing.JLabel();
        jLabelNom = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jLabelLatitude = new javax.swing.JLabel();
        jTextFieldLatitude = new javax.swing.JTextField();
        jLabelLongitude = new javax.swing.JLabel();
        jTextFieldLongitude = new javax.swing.JTextField();
        jButtonModifierPoint = new javax.swing.JButton();
        jButtonSupprimerPoint = new javax.swing.JButton();
        jToggleButtonDeplacerPoint = new javax.swing.JToggleButton();
        jComboBoxTypePoint = new javax.swing.JComboBox();
        jButtonModifierPointType = new javax.swing.JButton();
        jPanelDetailsSegmentsTitre = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelDetailsSegments = new javax.swing.JLabel();
        jToggleButtonMinimizeDetailsSegments = new javax.swing.JToggleButton();
        jPanelDetailsSegments = new javax.swing.JPanel();
        jComboBoxListeSegments = new javax.swing.JComboBox();
        jButtonModifierSegment = new javax.swing.JButton();
        jButtonSupprimerSegment = new javax.swing.JButton();
        jPanelReseauContainer = new javax.swing.JPanel();
        jPanelReseau = new javax.swing.JPanel();
        jPanelReseauOutilsTitre = new javax.swing.JPanel();
        jSeparatorReseauOutilsTitre = new javax.swing.JSeparator();
        jLabelOutilsReseau = new javax.swing.JLabel();
        jToggleButtonMinimizeBoutonsReseau = new javax.swing.JToggleButton();
        jPanelOutilsReseau = new javax.swing.JPanel();
        jToggleButtonCircuit = new javax.swing.JToggleButton();
        jToggleButtonItineraire = new javax.swing.JToggleButton();
        jPanelCircuitsTitre = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabelCircuitsTitre = new javax.swing.JLabel();
        jToggleButtonMinimizeReseauCirc = new javax.swing.JToggleButton();
        jPanelListeCircuits = new javax.swing.JPanel();
        jComboBoxListeCircuits = new javax.swing.JComboBox();
        jButtonModifierCircuit = new javax.swing.JButton();
        jToggleButtonEditerSource = new javax.swing.JToggleButton();
        jButtonRefreshCircuits = new javax.swing.JButton();
        jButtonSupprimerCircuit = new javax.swing.JButton();
        jPanelTitreReseauItineraire = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jToggleButtonMinimizeReseauIti = new javax.swing.JToggleButton();
        jLabelCircuitsTitre1 = new javax.swing.JLabel();
        jPanelReseauListeIti = new javax.swing.JPanel();
        jComboBoxListeItineraire = new javax.swing.JComboBox();
        jButtonSupprimerItineraire = new javax.swing.JButton();
        jButtonRefreshListeIti = new javax.swing.JButton();
        jPanelSimulationContainer = new javax.swing.JPanel();
        jPanelSimulation = new javax.swing.JPanel();
        jPanelSimulationOutilsTitre = new javax.swing.JPanel();
        jSeparatorSimulationOutilsTitre = new javax.swing.JSeparator();
        jLabelOutilsSimulation = new javax.swing.JLabel();
        jToggleButtonMinimizeBoutonsSimulation = new javax.swing.JToggleButton();
        jPanelSimulationOutils = new javax.swing.JPanel();
        jToggleButtonDemarrerSim = new javax.swing.JToggleButton();
        jButtonArreterSimulation = new javax.swing.JButton();
        jSliderVitesseSimulation = new javax.swing.JSlider();
        jLabelValueSpeed = new javax.swing.JLabel();
        jComboBoxListeSims = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldHeureCourante = new javax.swing.JTextField();
        jLabelHeureDebSim = new javax.swing.JLabel();
        jTextFieldHeureDebut = new javax.swing.JTextField();
        jLabelHeureFinSim = new javax.swing.JLabel();
        jTextFieldHeureFin = new javax.swing.JTextField();
        jButtonRapport = new javax.swing.JButton();
        jPanelSimulationParametresTitre = new javax.swing.JPanel();
        jSeparatorSimulationParametres = new javax.swing.JSeparator();
        jLabelSimulationParametresTitre = new javax.swing.JLabel();
        jButtonMinimiseParamSim = new javax.swing.JButton();
        jPanelSimulationParametres = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,1970);
        cal.set(Calendar.MONTH,0);
        cal.set(Calendar.DAY_OF_MONTH, 01);
        cal.set(Calendar.HOUR_OF_DAY,05);
        cal.set(Calendar.MINUTE,00);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        Date dateTemp = cal.getTime();
        SpinnerDateModel model= new SpinnerDateModel(dateTemp,null,null,Calendar.HOUR_OF_DAY);
        jSpinnerDebutSim = new javax.swing.JSpinner(model);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR,1970);
        cal2.set(Calendar.MONTH,0);
        cal2.set(Calendar.DAY_OF_MONTH, 01);
        cal2.set(Calendar.HOUR_OF_DAY,01);
        cal2.set(Calendar.MINUTE,00);
        cal2.set(Calendar.SECOND,0);
        cal2.set(Calendar.MILLISECOND,0);
        Date dateTemp2 = cal2.getTime();
        SpinnerDateModel model2= new SpinnerDateModel(dateTemp2,null,null,Calendar.HOUR_OF_DAY);
        jSpinnerFinSim = new javax.swing.JSpinner(model2);
        jLabelNomSImul = new javax.swing.JLabel();
        jTextFieldNomSim = new javax.swing.JTextField();
        jSpinnerQteSim = new javax.swing.JSpinner();
        SpinnerNumberModel modelQteSim = new SpinnerNumberModel(1, 1, 1000, 1);
        jSpinnerQteSim.setModel(modelQteSim);
        jLabel5 = new javax.swing.JLabel();
        jButtonCreerSim = new javax.swing.JButton();
        jPanelCenter = new javax.swing.JPanel();
        jScrollPaneCenter = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuOuvrir = new javax.swing.JMenuItem();
        jMenuItemEnregistrer = new javax.swing.JMenuItem();
        jMenuItemInformation = new javax.swing.JMenuItem();
        jMenuItemQuitter = new javax.swing.JMenuItem();
        jMenuEdition = new javax.swing.JMenu();
        jMenuItemAnnuler = new javax.swing.JMenuItem();
        jMenuItemRepeter = new javax.swing.JMenuItem();
        jMenuOutils = new javax.swing.JMenu();
        jMenuItemPreferences = new javax.swing.JMenuItem();
        jMenuAide = new javax.swing.JMenu();
        jMenuItemAPropos = new javax.swing.JMenuItem();

        jMenuItem8.setText("jMenuItem8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SimulatHEURE");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setPreferredSize(new java.awt.Dimension(1175, 800));
        setSize(new java.awt.Dimension(1175, 800));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanelBackMain.setPreferredSize(new java.awt.Dimension(1175, 800));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1175, 750));

        jPanelPrincipalResizable.setPreferredSize(new java.awt.Dimension(1175, 750));

        jPanelButton.setBackground(new java.awt.Color(204, 204, 204));
        jPanelButton.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));

        jLabelX.setText("Lon : ");

        jLabelXCoordonnees.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelXCoordonnees.setForeground(new java.awt.Color(0, 102, 255));

        jLabelY.setText("Lat : ");

        jLabelYCoordonnees.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelYCoordonnees.setForeground(new java.awt.Color(0, 102, 255));

        jButtonZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/zoonIn1.png"))); // NOI18N
        jButtonZoomIn.setToolTipText("Retour action");
        jButtonZoomIn.setBorderPainted(false);
        jButtonZoomIn.setContentAreaFilled(false);
        jButtonZoomIn.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/zoomIn2.png"))); // NOI18N
        jButtonZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZoomInActionPerformed(evt);
            }
        });

        jButtonZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/zoomOut1.png"))); // NOI18N
        jButtonZoomOut.setToolTipText("Retour action");
        jButtonZoomOut.setBorderPainted(false);
        jButtonZoomOut.setContentAreaFilled(false);
        jButtonZoomOut.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/zoomOut2.png"))); // NOI18N
        jButtonZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZoomOutActionPerformed(evt);
            }
        });

        jLabelNomProjet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelZoom.setText("Zoom : 1.0");

        jComboBoxTypeCarte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Simulation", "Réelle", "Aucune" }));
        jComboBoxTypeCarte.setToolTipText("Type de carte");
        jComboBoxTypeCarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeCarteActionPerformed(evt);
            }
        });

        jLabel1.setText("Affichage : ");

        javax.swing.GroupLayout jPanelButtonLayout = new javax.swing.GroupLayout(jPanelButton);
        jPanelButton.setLayout(jPanelButtonLayout);
        jPanelButtonLayout.setHorizontalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelXCoordonnees, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelYCoordonnees, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNomProjet, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxTypeCarte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButtonZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelButtonLayout.setVerticalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jButtonZoomIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButtonZoomOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelNomProjet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelYCoordonnees, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelY, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelXCoordonnees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelZoom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jComboBoxTypeCarte)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelTop.setBackground(new java.awt.Color(204, 204, 204));
        jPanelTop.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));

        jButtonNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/new.png"))); // NOI18N
        jButtonNew.setBorderPainted(false);
        jButtonNew.setContentAreaFilled(false);
        jButtonNew.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/new2.png"))); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/folder.png"))); // NOI18N
        jButtonOpen.setBorderPainted(false);
        jButtonOpen.setContentAreaFilled(false);
        jButtonOpen.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/folder2.png"))); // NOI18N
        jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenActionPerformed(evt);
            }
        });

        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save.png"))); // NOI18N
        jButtonSave.setBorderPainted(false);
        jButtonSave.setContentAreaFilled(false);
        jButtonSave.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save2.png"))); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/undo1.png"))); // NOI18N
        jButtonUndo.setToolTipText("Retour action");
        jButtonUndo.setBorderPainted(false);
        jButtonUndo.setContentAreaFilled(false);
        jButtonUndo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/undo2.png"))); // NOI18N
        jButtonUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUndoActionPerformed(evt);
            }
        });

        jButtonRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/redo1.png"))); // NOI18N
        jButtonRedo.setToolTipText("Répéter action");
        jButtonRedo.setBorderPainted(false);
        jButtonRedo.setContentAreaFilled(false);
        jButtonRedo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/redo2.png"))); // NOI18N
        jButtonRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRedoActionPerformed(evt);
            }
        });

        jComboBoxMode.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jComboBoxMode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Standard", "Édition", "Simulation" }));
        jComboBoxMode.setEnabled(false);

        jLabel6.setText("Mode : ");

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButtonNew, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRedo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxMode, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMode)
                            .addComponent(jLabel6))
                        .addContainerGap())
                    .addGroup(jPanelTopLayout.createSequentialGroup()
                        .addGroup(jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonOpen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonRedo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))))
        );

        jPanelLeft.setBackground(new java.awt.Color(204, 204, 204));
        jPanelLeft.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153)));
        jPanelLeft.setMaximumSize(new java.awt.Dimension(252, 509));
        jPanelLeft.setPreferredSize(new java.awt.Dimension(252, 509));

        jTabbedPaneGauche.setBackground(new java.awt.Color(204, 204, 204));
        jTabbedPaneGauche.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPaneGauche.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneGaucheStateChanged(evt);
            }
        });

        jPanelGraphe.setBackground(new java.awt.Color(204, 204, 204));

        jPanelBoutonsGrapheTitre.setBackground(new java.awt.Color(204, 204, 204));

        jToggleButtonMinimizeBoutonsGraphe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsGraphe.setContentAreaFilled(false);
        jToggleButtonMinimizeBoutonsGraphe.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsGraphe.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsGraphe.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsGraphe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeBoutonsGrapheActionPerformed(evt);
            }
        });

        jLabelOutilsGraphe.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelOutilsGraphe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOutilsGraphe.setText("Outils");

        javax.swing.GroupLayout jPanelBoutonsGrapheTitreLayout = new javax.swing.GroupLayout(jPanelBoutonsGrapheTitre);
        jPanelBoutonsGrapheTitre.setLayout(jPanelBoutonsGrapheTitreLayout);
        jPanelBoutonsGrapheTitreLayout.setHorizontalGroup(
            jPanelBoutonsGrapheTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparatorTopGraphe)
            .addGroup(jPanelBoutonsGrapheTitreLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelOutilsGraphe, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonMinimizeBoutonsGraphe, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelBoutonsGrapheTitreLayout.setVerticalGroup(
            jPanelBoutonsGrapheTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutonsGrapheTitreLayout.createSequentialGroup()
                .addComponent(jSeparatorTopGraphe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBoutonsGrapheTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonMinimizeBoutonsGraphe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOutilsGraphe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelBoutonsGraphe.setBackground(new java.awt.Color(204, 204, 204));

        jToggleButtonPoint.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jToggleButtonPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/dot1.png"))); // NOI18N
        jToggleButtonPoint.setText("Point");
        jToggleButtonPoint.setToolTipText("Mode point");
        jToggleButtonPoint.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/dot2.png"))); // NOI18N
        jToggleButtonPoint.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/dot2.png"))); // NOI18N
        jToggleButtonPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPointActionPerformed(evt);
            }
        });

        jToggleButtonSegment.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jToggleButtonSegment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/arrow1.png"))); // NOI18N
        jToggleButtonSegment.setText("Segment");
        jToggleButtonSegment.setToolTipText("Mode segment");
        jToggleButtonSegment.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/arrow2.png"))); // NOI18N
        jToggleButtonSegment.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/arrow2.png"))); // NOI18N
        jToggleButtonSegment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSegmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoutonsGrapheLayout = new javax.swing.GroupLayout(jPanelBoutonsGraphe);
        jPanelBoutonsGraphe.setLayout(jPanelBoutonsGrapheLayout);
        jPanelBoutonsGrapheLayout.setHorizontalGroup(
            jPanelBoutonsGrapheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutonsGrapheLayout.createSequentialGroup()
                .addComponent(jToggleButtonPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButtonSegment, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBoutonsGrapheLayout.setVerticalGroup(
            jPanelBoutonsGrapheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBoutonsGrapheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBoutonsGrapheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonPoint)
                    .addComponent(jToggleButtonSegment))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelDetailsSelectionTitre.setBackground(new java.awt.Color(204, 204, 204));

        jLabelDetailsSelection.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDetailsSelection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDetailsSelection.setText("Détails Sélection");

        jToggleButtonMinimizeDetailsSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSelection.setContentAreaFilled(false);
        jToggleButtonMinimizeDetailsSelection.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSelection.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSelection.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeDetailsSelectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetailsSelectionTitreLayout = new javax.swing.GroupLayout(jPanelDetailsSelectionTitre);
        jPanelDetailsSelectionTitre.setLayout(jPanelDetailsSelectionTitreLayout);
        jPanelDetailsSelectionTitreLayout.setHorizontalGroup(
            jPanelDetailsSelectionTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetailsSelectionTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetailsSelectionTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparatorDetailsSelection)
                    .addGroup(jPanelDetailsSelectionTitreLayout.createSequentialGroup()
                        .addComponent(jLabelDetailsSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonMinimizeDetailsSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelDetailsSelectionTitreLayout.setVerticalGroup(
            jPanelDetailsSelectionTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsSelectionTitreLayout.createSequentialGroup()
                .addComponent(jSeparatorDetailsSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanelDetailsSelectionTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelDetailsSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonMinimizeDetailsSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jPanelDetailsSelection.setBackground(new java.awt.Color(204, 204, 204));

        jLabelType.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelType.setText("Type : ");

        jLabelNom.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelNom.setText("Nom : ");

        jLabelLatitude.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelLatitude.setText("Latitude : ");

        jTextFieldLatitude.setEditable(false);

        jLabelLongitude.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelLongitude.setText("Longitude : ");

        jTextFieldLongitude.setEditable(false);

        jButtonModifierPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save16.png"))); // NOI18N
        jButtonModifierPoint.setToolTipText("Sauvegarder nom");
        jButtonModifierPoint.setContentAreaFilled(false);
        jButtonModifierPoint.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save20.png"))); // NOI18N
        jButtonModifierPoint.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save20.png"))); // NOI18N
        jButtonModifierPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierPointActionPerformed(evt);
            }
        });

        jButtonSupprimerPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete1.png"))); // NOI18N
        jButtonSupprimerPoint.setText("Supprimer");
        jButtonSupprimerPoint.setToolTipText("Supprimer point sélectionné");
        jButtonSupprimerPoint.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete2.png"))); // NOI18N
        jButtonSupprimerPoint.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete2.png"))); // NOI18N
        jButtonSupprimerPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerPointActionPerformed(evt);
            }
        });

        jToggleButtonDeplacerPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/move1.png"))); // NOI18N
        jToggleButtonDeplacerPoint.setText("Déplacer");
        jToggleButtonDeplacerPoint.setToolTipText("Déplacer le point sélectionné");
        jToggleButtonDeplacerPoint.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/move2.png"))); // NOI18N
        jToggleButtonDeplacerPoint.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/move2.png"))); // NOI18N
        jToggleButtonDeplacerPoint.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/move2.png"))); // NOI18N
        jToggleButtonDeplacerPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDeplacerPointActionPerformed(evt);
            }
        });

        jButtonModifierPointType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save16.png"))); // NOI18N
        jButtonModifierPointType.setToolTipText("Sauvegarder type");
        jButtonModifierPointType.setContentAreaFilled(false);
        jButtonModifierPointType.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save20.png"))); // NOI18N
        jButtonModifierPointType.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/save20.png"))); // NOI18N
        jButtonModifierPointType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierPointTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetailsSelectionLayout = new javax.swing.GroupLayout(jPanelDetailsSelection);
        jPanelDetailsSelection.setLayout(jPanelDetailsSelectionLayout);
        jPanelDetailsSelectionLayout.setHorizontalGroup(
            jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetailsSelectionLayout.createSequentialGroup()
                        .addComponent(jLabelLatitude)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLatitude))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetailsSelectionLayout.createSequentialGroup()
                        .addComponent(jLabelLongitude, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetailsSelectionLayout.createSequentialGroup()
                        .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNom)
                            .addComponent(jLabelType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxTypePoint, 0, 136, Short.MAX_VALUE)
                            .addComponent(jTextFieldNom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonModifierPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonModifierPointType, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanelDetailsSelectionLayout.createSequentialGroup()
                .addComponent(jToggleButtonDeplacerPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSupprimerPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDetailsSelectionLayout.setVerticalGroup(
            jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsSelectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelType)
                        .addComponent(jComboBoxTypePoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonModifierPointType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonModifierPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNom)
                        .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLatitude))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLongitude)
                    .addComponent(jTextFieldLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetailsSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonDeplacerPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSupprimerPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanelDetailsSegmentsTitre.setBackground(new java.awt.Color(204, 204, 204));

        jLabelDetailsSegments.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelDetailsSegments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDetailsSegments.setText("Détails Segments");

        jToggleButtonMinimizeDetailsSegments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSegments.setContentAreaFilled(false);
        jToggleButtonMinimizeDetailsSegments.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSegments.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSegments.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeDetailsSegments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeDetailsSegmentsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetailsSegmentsTitreLayout = new javax.swing.GroupLayout(jPanelDetailsSegmentsTitre);
        jPanelDetailsSegmentsTitre.setLayout(jPanelDetailsSegmentsTitreLayout);
        jPanelDetailsSegmentsTitreLayout.setHorizontalGroup(
            jPanelDetailsSegmentsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetailsSegmentsTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDetailsSegments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonMinimizeDetailsSegments, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelDetailsSegmentsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDetailsSegmentsTitreLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelDetailsSegmentsTitreLayout.setVerticalGroup(
            jPanelDetailsSegmentsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsSegmentsTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetailsSegmentsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButtonMinimizeDetailsSegments, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDetailsSegments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanelDetailsSegmentsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelDetailsSegmentsTitreLayout.createSequentialGroup()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 34, Short.MAX_VALUE)))
        );

        jPanelDetailsSegments.setBackground(new java.awt.Color(204, 204, 204));

        jComboBoxListeSegments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxListeSegmentsActionPerformed(evt);
            }
        });

        jButtonModifierSegment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/edit1.png"))); // NOI18N
        jButtonModifierSegment.setToolTipText("Modifier segment sélectionné");
        jButtonModifierSegment.setContentAreaFilled(false);
        jButtonModifierSegment.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/edit2.png"))); // NOI18N
        jButtonModifierSegment.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/edit2.png"))); // NOI18N
        jButtonModifierSegment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierSegmentActionPerformed(evt);
            }
        });

        jButtonSupprimerSegment.setText("Supprimer");
        jButtonSupprimerSegment.setToolTipText("Supprimer segment sélectionné");
        jButtonSupprimerSegment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerSegmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDetailsSegmentsLayout = new javax.swing.GroupLayout(jPanelDetailsSegments);
        jPanelDetailsSegments.setLayout(jPanelDetailsSegmentsLayout);
        jPanelDetailsSegmentsLayout.setHorizontalGroup(
            jPanelDetailsSegmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetailsSegmentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxListeSegments, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModifierSegment, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDetailsSegmentsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSupprimerSegment)
                .addGap(65, 65, 65))
        );
        jPanelDetailsSegmentsLayout.setVerticalGroup(
            jPanelDetailsSegmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsSegmentsLayout.createSequentialGroup()
                .addGroup(jPanelDetailsSegmentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonModifierSegment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxListeSegments, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSupprimerSegment))
        );

        javax.swing.GroupLayout jPanelGrapheLayout = new javax.swing.GroupLayout(jPanelGraphe);
        jPanelGraphe.setLayout(jPanelGrapheLayout);
        jPanelGrapheLayout.setHorizontalGroup(
            jPanelGrapheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelDetailsSegments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelDetailsSegmentsTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelBoutonsGraphe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelDetailsSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelBoutonsGrapheTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelDetailsSelectionTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelGrapheLayout.setVerticalGroup(
            jPanelGrapheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGrapheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelBoutonsGrapheTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBoutonsGraphe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetailsSelectionTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDetailsSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetailsSegmentsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetailsSegments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelGrapheContainerLayout = new javax.swing.GroupLayout(jPanelGrapheContainer);
        jPanelGrapheContainer.setLayout(jPanelGrapheContainerLayout);
        jPanelGrapheContainerLayout.setHorizontalGroup(
            jPanelGrapheContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraphe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelGrapheContainerLayout.setVerticalGroup(
            jPanelGrapheContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelGraphe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneGauche.addTab("Graphe", jPanelGrapheContainer);

        jPanelReseau.setBackground(new java.awt.Color(204, 204, 204));

        jPanelReseauOutilsTitre.setBackground(new java.awt.Color(204, 204, 204));

        jLabelOutilsReseau.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelOutilsReseau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOutilsReseau.setText("Outils");

        jToggleButtonMinimizeBoutonsReseau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsReseau.setContentAreaFilled(false);
        jToggleButtonMinimizeBoutonsReseau.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsReseau.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsReseau.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsReseau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeBoutonsReseauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelReseauOutilsTitreLayout = new javax.swing.GroupLayout(jPanelReseauOutilsTitre);
        jPanelReseauOutilsTitre.setLayout(jPanelReseauOutilsTitreLayout);
        jPanelReseauOutilsTitreLayout.setHorizontalGroup(
            jPanelReseauOutilsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReseauOutilsTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparatorReseauOutilsTitre))
            .addGroup(jPanelReseauOutilsTitreLayout.createSequentialGroup()
                .addComponent(jLabelOutilsReseau, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonMinimizeBoutonsReseau, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanelReseauOutilsTitreLayout.setVerticalGroup(
            jPanelReseauOutilsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReseauOutilsTitreLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparatorReseauOutilsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelReseauOutilsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonMinimizeBoutonsReseau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOutilsReseau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanelOutilsReseau.setBackground(new java.awt.Color(204, 204, 204));

        jToggleButtonCircuit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jToggleButtonCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/circuit1.png"))); // NOI18N
        jToggleButtonCircuit.setText("Créer Circuit");
        jToggleButtonCircuit.setToolTipText("Mode circuit");
        jToggleButtonCircuit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/circuit2.png"))); // NOI18N
        jToggleButtonCircuit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/circuit2.png"))); // NOI18N
        jToggleButtonCircuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCircuitActionPerformed(evt);
            }
        });

        jToggleButtonItineraire.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jToggleButtonItineraire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/pin1.png"))); // NOI18N
        jToggleButtonItineraire.setText("Créer itinéraire");
        jToggleButtonItineraire.setToolTipText("Mode itinéraire");
        jToggleButtonItineraire.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/pin2.png"))); // NOI18N
        jToggleButtonItineraire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonItineraireActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelOutilsReseauLayout = new javax.swing.GroupLayout(jPanelOutilsReseau);
        jPanelOutilsReseau.setLayout(jPanelOutilsReseauLayout);
        jPanelOutilsReseauLayout.setHorizontalGroup(
            jPanelOutilsReseauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOutilsReseauLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelOutilsReseauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonCircuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonItineraire, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelOutilsReseauLayout.setVerticalGroup(
            jPanelOutilsReseauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOutilsReseauLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButtonCircuit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButtonItineraire)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCircuitsTitre.setBackground(new java.awt.Color(204, 204, 204));

        jLabelCircuitsTitre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCircuitsTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCircuitsTitre.setText("Liste des Circuits");

        jToggleButtonMinimizeReseauCirc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeReseauCirc.setContentAreaFilled(false);
        jToggleButtonMinimizeReseauCirc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeReseauCirc.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeReseauCirc.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeReseauCirc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeReseauCircActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCircuitsTitreLayout = new javax.swing.GroupLayout(jPanelCircuitsTitre);
        jPanelCircuitsTitre.setLayout(jPanelCircuitsTitreLayout);
        jPanelCircuitsTitreLayout.setHorizontalGroup(
            jPanelCircuitsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCircuitsTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCircuitsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCircuitsTitreLayout.createSequentialGroup()
                        .addComponent(jSeparator5)
                        .addContainerGap())
                    .addGroup(jPanelCircuitsTitreLayout.createSequentialGroup()
                        .addComponent(jLabelCircuitsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonMinimizeReseauCirc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );
        jPanelCircuitsTitreLayout.setVerticalGroup(
            jPanelCircuitsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCircuitsTitreLayout.createSequentialGroup()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelCircuitsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonMinimizeReseauCirc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCircuitsTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelListeCircuits.setBackground(new java.awt.Color(204, 204, 204));

        jComboBoxListeCircuits.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liste des Circuits" }));
        jComboBoxListeCircuits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxListeCircuitsActionPerformed(evt);
            }
        });

        jButtonModifierCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/edit1.png"))); // NOI18N
        jButtonModifierCircuit.setToolTipText("Modifier le circuit sélectionné");
        jButtonModifierCircuit.setContentAreaFilled(false);
        jButtonModifierCircuit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/edit2.png"))); // NOI18N
        jButtonModifierCircuit.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/edit2.png"))); // NOI18N
        jButtonModifierCircuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierCircuitActionPerformed(evt);
            }
        });

        jToggleButtonEditerSource.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/pin1.png"))); // NOI18N
        jToggleButtonEditerSource.setText("Modifier départ");
        jToggleButtonEditerSource.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/pin2.png"))); // NOI18N
        jToggleButtonEditerSource.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/pin2.png"))); // NOI18N
        jToggleButtonEditerSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonEditerSourceActionPerformed(evt);
            }
        });

        jButtonRefreshCircuits.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/refresh1.png"))); // NOI18N
        jButtonRefreshCircuits.setToolTipText("Actualiser");
        jButtonRefreshCircuits.setBorderPainted(false);
        jButtonRefreshCircuits.setContentAreaFilled(false);
        jButtonRefreshCircuits.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/refresh2.png"))); // NOI18N
        jButtonRefreshCircuits.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/refresh2.png"))); // NOI18N
        jButtonRefreshCircuits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshCircuitsActionPerformed(evt);
            }
        });

        jButtonSupprimerCircuit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete1.png"))); // NOI18N
        jButtonSupprimerCircuit.setToolTipText("Supprimer le circuit sélectionné");
        jButtonSupprimerCircuit.setContentAreaFilled(false);
        jButtonSupprimerCircuit.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete2.png"))); // NOI18N
        jButtonSupprimerCircuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerCircuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelListeCircuitsLayout = new javax.swing.GroupLayout(jPanelListeCircuits);
        jPanelListeCircuits.setLayout(jPanelListeCircuitsLayout);
        jPanelListeCircuitsLayout.setHorizontalGroup(
            jPanelListeCircuitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListeCircuitsLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonRefreshCircuits, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelListeCircuitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxListeCircuits, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButtonEditerSource, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModifierCircuit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSupprimerCircuit, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelListeCircuitsLayout.setVerticalGroup(
            jPanelListeCircuitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListeCircuitsLayout.createSequentialGroup()
                .addGroup(jPanelListeCircuitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxListeCircuits, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModifierCircuit)
                    .addComponent(jButtonRefreshCircuits)
                    .addComponent(jButtonSupprimerCircuit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonEditerSource))
        );

        jPanelTitreReseauItineraire.setBackground(new java.awt.Color(204, 204, 204));

        jToggleButtonMinimizeReseauIti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeReseauIti.setContentAreaFilled(false);
        jToggleButtonMinimizeReseauIti.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeReseauIti.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeReseauIti.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeReseauIti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeReseauItiActionPerformed(evt);
            }
        });

        jLabelCircuitsTitre1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelCircuitsTitre1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCircuitsTitre1.setText("Liste des Itinéraires");

        javax.swing.GroupLayout jPanelTitreReseauItineraireLayout = new javax.swing.GroupLayout(jPanelTitreReseauItineraire);
        jPanelTitreReseauItineraire.setLayout(jPanelTitreReseauItineraireLayout);
        jPanelTitreReseauItineraireLayout.setHorizontalGroup(
            jPanelTitreReseauItineraireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitreReseauItineraireLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTitreReseauItineraireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTitreReseauItineraireLayout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())
                    .addGroup(jPanelTitreReseauItineraireLayout.createSequentialGroup()
                        .addComponent(jLabelCircuitsTitre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonMinimizeReseauIti, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanelTitreReseauItineraireLayout.setVerticalGroup(
            jPanelTitreReseauItineraireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitreReseauItineraireLayout.createSequentialGroup()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelTitreReseauItineraireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButtonMinimizeReseauIti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelCircuitsTitre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanelReseauListeIti.setBackground(new java.awt.Color(204, 204, 204));

        jComboBoxListeItineraire.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Liste des Itinéraires" }));
        jComboBoxListeItineraire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxListeItineraireActionPerformed(evt);
            }
        });

        jButtonSupprimerItineraire.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete1.png"))); // NOI18N
        jButtonSupprimerItineraire.setToolTipText("Supprime l'itinéraire sélectionné");
        jButtonSupprimerItineraire.setContentAreaFilled(false);
        jButtonSupprimerItineraire.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete2.png"))); // NOI18N
        jButtonSupprimerItineraire.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/delete2.png"))); // NOI18N
        jButtonSupprimerItineraire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerItineraireActionPerformed(evt);
            }
        });

        jButtonRefreshListeIti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/refresh1.png"))); // NOI18N
        jButtonRefreshListeIti.setToolTipText("Actualiser");
        jButtonRefreshListeIti.setBorderPainted(false);
        jButtonRefreshListeIti.setContentAreaFilled(false);
        jButtonRefreshListeIti.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/refresh2.png"))); // NOI18N
        jButtonRefreshListeIti.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/refresh2.png"))); // NOI18N
        jButtonRefreshListeIti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshListeItiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelReseauListeItiLayout = new javax.swing.GroupLayout(jPanelReseauListeIti);
        jPanelReseauListeIti.setLayout(jPanelReseauListeItiLayout);
        jPanelReseauListeItiLayout.setHorizontalGroup(
            jPanelReseauListeItiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReseauListeItiLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButtonRefreshListeIti, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxListeItineraire, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSupprimerItineraire, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelReseauListeItiLayout.setVerticalGroup(
            jPanelReseauListeItiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBoxListeItineraire, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButtonSupprimerItineraire)
            .addComponent(jButtonRefreshListeIti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelReseauLayout = new javax.swing.GroupLayout(jPanelReseau);
        jPanelReseau.setLayout(jPanelReseauLayout);
        jPanelReseauLayout.setHorizontalGroup(
            jPanelReseauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitreReseauItineraire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelOutilsReseau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelReseauListeIti, javax.swing.GroupLayout.PREFERRED_SIZE, 243, Short.MAX_VALUE)
            .addComponent(jPanelCircuitsTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelListeCircuits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelReseauOutilsTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelReseauLayout.setVerticalGroup(
            jPanelReseauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReseauLayout.createSequentialGroup()
                .addComponent(jPanelReseauOutilsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOutilsReseau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelCircuitsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelListeCircuits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelTitreReseauItineraire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelReseauListeIti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelReseauContainerLayout = new javax.swing.GroupLayout(jPanelReseauContainer);
        jPanelReseauContainer.setLayout(jPanelReseauContainerLayout);
        jPanelReseauContainerLayout.setHorizontalGroup(
            jPanelReseauContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelReseau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelReseauContainerLayout.setVerticalGroup(
            jPanelReseauContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelReseau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneGauche.addTab("Réseau", jPanelReseauContainer);

        jPanelSimulation.setBackground(new java.awt.Color(204, 204, 204));

        jPanelSimulationOutilsTitre.setBackground(new java.awt.Color(204, 204, 204));

        jLabelOutilsSimulation.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelOutilsSimulation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOutilsSimulation.setText("Simulation");

        jToggleButtonMinimizeBoutonsSimulation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsSimulation.setContentAreaFilled(false);
        jToggleButtonMinimizeBoutonsSimulation.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsSimulation.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsSimulation.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jToggleButtonMinimizeBoutonsSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMinimizeBoutonsSimulationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSimulationOutilsTitreLayout = new javax.swing.GroupLayout(jPanelSimulationOutilsTitre);
        jPanelSimulationOutilsTitre.setLayout(jPanelSimulationOutilsTitreLayout);
        jPanelSimulationOutilsTitreLayout.setHorizontalGroup(
            jPanelSimulationOutilsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationOutilsTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparatorSimulationOutilsTitre))
            .addGroup(jPanelSimulationOutilsTitreLayout.createSequentialGroup()
                .addComponent(jLabelOutilsSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonMinimizeBoutonsSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        jPanelSimulationOutilsTitreLayout.setVerticalGroup(
            jPanelSimulationOutilsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationOutilsTitreLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparatorSimulationOutilsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSimulationOutilsTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButtonMinimizeBoutonsSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelOutilsSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanelSimulationOutils.setBackground(new java.awt.Color(204, 204, 204));

        jToggleButtonDemarrerSim.setText("Démarrer");
        jToggleButtonDemarrerSim.setToolTipText("Démarrer la simulation");
        jToggleButtonDemarrerSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDemarrerSimActionPerformed(evt);
            }
        });

        jButtonArreterSimulation.setText("Arrêter");
        jButtonArreterSimulation.setToolTipText("Arrêter la simulation");
        jButtonArreterSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonArreterSimulationActionPerformed(evt);
            }
        });

        jSliderVitesseSimulation.setBackground(new java.awt.Color(204, 204, 204));
        jSliderVitesseSimulation.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jSliderVitesseSimulation.setForeground(new java.awt.Color(0, 0, 0));
        jSliderVitesseSimulation.setMaximum(20);
        jSliderVitesseSimulation.setMinimum(-20);
        jSliderVitesseSimulation.setPaintTicks(true);
        jSliderVitesseSimulation.setValue(0);
        jSliderVitesseSimulation.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderVitesseSimulationStateChanged(evt);
            }
        });

        jLabelValueSpeed.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelValueSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jComboBoxListeSims.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxListeSimsActionPerformed(evt);
            }
        });

        jLabel4.setText("Heure courante : ");

        jTextFieldHeureCourante.setEditable(false);
        jTextFieldHeureCourante.setToolTipText("Heure courante");

        jLabelHeureDebSim.setText("Heure de début : ");

        jTextFieldHeureDebut.setEditable(false);
        jTextFieldHeureDebut.setToolTipText("Heure de début de la simulation");

        jLabelHeureFinSim.setText("Heure de fin :  ");

        jTextFieldHeureFin.setEditable(false);
        jTextFieldHeureFin.setToolTipText("Heure de fin de la simulation");

        jButtonRapport.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButtonRapport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/stats.png"))); // NOI18N
        jButtonRapport.setText("Rapport");
        jButtonRapport.setToolTipText("Afficher rapport");
        jButtonRapport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRapportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSimulationOutilsLayout = new javax.swing.GroupLayout(jPanelSimulationOutils);
        jPanelSimulationOutils.setLayout(jPanelSimulationOutilsLayout);
        jPanelSimulationOutilsLayout.setHorizontalGroup(
            jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                        .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                                .addComponent(jToggleButtonDemarrerSim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonArreterSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxListeSims, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addComponent(jLabelValueSpeed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSliderVitesseSimulation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                        .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                                .addComponent(jLabelHeureDebSim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldHeureDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldHeureCourante, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                                .addComponent(jLabelHeureFinSim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldHeureFin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSimulationOutilsLayout.createSequentialGroup()
                        .addComponent(jButtonRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanelSimulationOutilsLayout.setVerticalGroup(
            jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationOutilsLayout.createSequentialGroup()
                .addComponent(jComboBoxListeSims, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonDemarrerSim)
                    .addComponent(jButtonArreterSimulation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelValueSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSliderVitesseSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldHeureCourante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHeureDebSim)
                    .addComponent(jTextFieldHeureDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSimulationOutilsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHeureFinSim)
                    .addComponent(jTextFieldHeureFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRapport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSliderVitesseSimulation.setValue(0);

        jPanelSimulationParametresTitre.setBackground(new java.awt.Color(204, 204, 204));

        jLabelSimulationParametresTitre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabelSimulationParametresTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSimulationParametresTitre.setText("Création de simulations");

        jButtonMinimiseParamSim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize1.png"))); // NOI18N
        jButtonMinimiseParamSim.setContentAreaFilled(false);
        jButtonMinimiseParamSim.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/minimize2.png"))); // NOI18N
        jButtonMinimiseParamSim.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize2.png"))); // NOI18N
        jButtonMinimiseParamSim.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation/images/maximize1.png"))); // NOI18N
        jButtonMinimiseParamSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinimiseParamSimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSimulationParametresTitreLayout = new javax.swing.GroupLayout(jPanelSimulationParametresTitre);
        jPanelSimulationParametresTitre.setLayout(jPanelSimulationParametresTitreLayout);
        jPanelSimulationParametresTitreLayout.setHorizontalGroup(
            jPanelSimulationParametresTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationParametresTitreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSimulationParametresTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparatorSimulationParametres)
                    .addGroup(jPanelSimulationParametresTitreLayout.createSequentialGroup()
                        .addComponent(jLabelSimulationParametresTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonMinimiseParamSim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSimulationParametresTitreLayout.setVerticalGroup(
            jPanelSimulationParametresTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationParametresTitreLayout.createSequentialGroup()
                .addComponent(jSeparatorSimulationParametres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSimulationParametresTitreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSimulationParametresTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMinimiseParamSim)))
        );

        jPanelSimulationParametres.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setText("Heure de début : ");

        jLabel3.setText("Heure de fin : ");

        JSpinner.DateEditor editor = new JSpinner.DateEditor(jSpinnerDebutSim,"HH:mm");
        jSpinnerDebutSim.setEditor(editor);
        JFormattedTextField tfDebSim = ((JSpinner.DefaultEditor) jSpinnerDebutSim.getEditor()).getTextField();
        tfDebSim.setEditable(false);
        jSpinnerDebutSim.setToolTipText("Heure de début");

        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(jSpinnerFinSim,"HH:mm");
        jSpinnerFinSim.setEditor(editor2);
        JFormattedTextField tfFinSim = ((JSpinner.DefaultEditor) jSpinnerFinSim.getEditor()).getTextField();
        tfFinSim.setEditable(false);
        jSpinnerFinSim.setToolTipText("Heure de fin");

        jLabelNomSImul.setText("Nom Simulation : ");

        jTextFieldNomSim.setToolTipText("Nom de la simulation");

        jSpinnerQteSim.setToolTipText("Nombre de simulation à générer");
        jSpinnerQteSim.setValue(1);

        jLabel5.setText("Quantité(s) : ");

        jButtonCreerSim.setText("Créer simulation(s)");
        jButtonCreerSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreerSimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSimulationParametresLayout = new javax.swing.GroupLayout(jPanelSimulationParametres);
        jPanelSimulationParametres.setLayout(jPanelSimulationParametresLayout);
        jPanelSimulationParametresLayout.setHorizontalGroup(
            jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                        .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(33, 33, 33)
                                .addComponent(jSpinnerFinSim))
                            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jSpinnerDebutSim)))
                        .addGap(9, 9, 9))
                    .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                        .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                                .addComponent(jLabelNomSImul)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNomSim, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinnerQteSim, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCreerSim)
                .addGap(47, 47, 47))
        );
        jPanelSimulationParametresLayout.setVerticalGroup(
            jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationParametresLayout.createSequentialGroup()
                .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomSImul)
                    .addComponent(jTextFieldNomSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinnerDebutSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jSpinnerFinSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSimulationParametresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinnerQteSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCreerSim)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelSimulationLayout = new javax.swing.GroupLayout(jPanelSimulation);
        jPanelSimulation.setLayout(jPanelSimulationLayout);
        jPanelSimulationLayout.setHorizontalGroup(
            jPanelSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSimulationParametresTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelSimulationLayout.createSequentialGroup()
                .addComponent(jPanelSimulationParametres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelSimulationLayout.createSequentialGroup()
                .addGroup(jPanelSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanelSimulationOutils, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSimulationOutilsTitre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelSimulationLayout.setVerticalGroup(
            jPanelSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSimulationLayout.createSequentialGroup()
                .addComponent(jPanelSimulationOutilsTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelSimulationOutils, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelSimulationParametresTitre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelSimulationParametres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelSimulationContainerLayout = new javax.swing.GroupLayout(jPanelSimulationContainer);
        jPanelSimulationContainer.setLayout(jPanelSimulationContainerLayout);
        jPanelSimulationContainerLayout.setHorizontalGroup(
            jPanelSimulationContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelSimulationContainerLayout.setVerticalGroup(
            jPanelSimulationContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPaneGauche.addTab("Simulation", jPanelSimulationContainer);

        javax.swing.GroupLayout jPanelLeftLayout = new javax.swing.GroupLayout(jPanelLeft);
        jPanelLeft.setLayout(jPanelLeftLayout);
        jPanelLeftLayout.setHorizontalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneGauche)
        );
        jPanelLeftLayout.setVerticalGroup(
            jPanelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeftLayout.createSequentialGroup()
                .addComponent(jTabbedPaneGauche, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelCenter.setBackground(new java.awt.Color(204, 204, 204));
        jPanelCenter.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51)));
        jPanelCenter.setForeground(new java.awt.Color(255, 255, 255));
        jPanelCenter.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanelCenterComponentResized(evt);
            }
        });

        jScrollPaneCenter.setPreferredSize(new java.awt.Dimension(2000, 1000));

        jPanel1.setMaximumSize(new java.awt.Dimension(2000, 1000));
        jPanel1.setPreferredSize(new java.awt.Dimension(2000, 1000));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2000, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );

        jScrollPaneCenter.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanelCenterLayout = new javax.swing.GroupLayout(jPanelCenter);
        jPanelCenter.setLayout(jPanelCenterLayout);
        jPanelCenterLayout.setHorizontalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneCenter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelCenterLayout.setVerticalGroup(
            jPanelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneCenter, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelPrincipalResizableLayout = new javax.swing.GroupLayout(jPanelPrincipalResizable);
        jPanelPrincipalResizable.setLayout(jPanelPrincipalResizableLayout);
        jPanelPrincipalResizableLayout.setHorizontalGroup(
            jPanelPrincipalResizableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalResizableLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalResizableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalResizableLayout.createSequentialGroup()
                        .addComponent(jPanelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelPrincipalResizableLayout.setVerticalGroup(
            jPanelPrincipalResizableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPrincipalResizableLayout.createSequentialGroup()
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPrincipalResizableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setViewportView(jPanelPrincipalResizable);

        javax.swing.GroupLayout jPanelBackMainLayout = new javax.swing.GroupLayout(jPanelBackMain);
        jPanelBackMain.setLayout(jPanelBackMainLayout);
        jPanelBackMainLayout.setHorizontalGroup(
            jPanelBackMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelBackMainLayout.setVerticalGroup(
            jPanelBackMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
        );

        jMenuBar1.setPreferredSize(new java.awt.Dimension(56, 31));

        jMenuFichier.setText("Fichier");

        jMenuOuvrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuOuvrir.setText("Ouvrir");
        jMenuOuvrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOuvrirActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuOuvrir);

        jMenuItemEnregistrer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEnregistrer.setText("Enregistrer");
        jMenuItemEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnregistrerActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemEnregistrer);

        jMenuItemInformation.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemInformation.setText("Information");
        jMenuItemInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInformationActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemInformation);

        jMenuItemQuitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemQuitter.setText("Quitter");
        jMenuItemQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemQuitterActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemQuitter);

        jMenuBar1.add(jMenuFichier);

        jMenuEdition.setText("Édition");

        jMenuItemAnnuler.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAnnuler.setText("Annuler");
        jMenuItemAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAnnulerActionPerformed(evt);
            }
        });
        jMenuEdition.add(jMenuItemAnnuler);

        jMenuItemRepeter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRepeter.setText("Répéter");
        jMenuItemRepeter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRepeterActionPerformed(evt);
            }
        });
        jMenuEdition.add(jMenuItemRepeter);

        jMenuBar1.add(jMenuEdition);

        jMenuOutils.setText("Outils");

        jMenuItemPreferences.setText("Préférences");
        jMenuOutils.add(jMenuItemPreferences);

        jMenuBar1.add(jMenuOutils);

        jMenuAide.setText("Aide");

        jMenuItemAPropos.setText("À Propos");
        jMenuItemAPropos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAProposActionPerformed(evt);
            }
        });
        jMenuAide.add(jMenuItemAPropos);

        jMenuBar1.add(jMenuAide);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackMain, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");
        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
      
    /**
     * Action du bouton Arret
     * @param evt Event
     */
    private void jToggleButtonPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPointActionPerformed
        if(jToggleButtonPoint.isSelected()){
            chargerModeEdition();
            deselectionBoutons();
            jToggleButtonPoint.setSelected(true);
            dessinateur.updateApplication(application);
            dessinateur.repaint();
        }
        else{
            deselectionBoutons();
            chargerModeStandard();
        }
    }//GEN-LAST:event_jToggleButtonPointActionPerformed
    
    /**
     * Action du bouton Segment
     * @param evt Event
     */
    private void jToggleButtonSegmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSegmentActionPerformed
        
        if(jToggleButtonSegment.isSelected()){
            dessinateur.resetSurbrillanceSegment();
            chargerModeEdition();
            deselectionBoutons();
            jToggleButtonSegment.setSelected(true);
            dessinateur.updateApplication(application);
            dessinateur.repaint();
        }
        else{
            iterSegment = 0;
            deselectionBoutons();
            chargerModeStandard();
        }
    }//GEN-LAST:event_jToggleButtonSegmentActionPerformed

    /**
     * ACTION DU BOUTON CIRCUIT
     * @param evt Event
     */
    private void jToggleButtonCircuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCircuitActionPerformed
        if(jToggleButtonCircuit.isSelected()){
            chargerModeEdition();
            resetVarReseau();
            jToggleButtonCircuit.setSelected(true);
        }
        else{
            chargerModeStandard();
            jToggleButtonCircuit.setSelected(false);
            updateCarte();
        }
    }//GEN-LAST:event_jToggleButtonCircuitActionPerformed

    /**
     * Action du bouton Itineraire
     * @param evt Event
     */
    private void jToggleButtonItineraireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonItineraireActionPerformed
        if(jToggleButtonItineraire.isSelected()){
            chargerModeEdition();
            deselectionBoutons();
            jToggleButtonItineraire.setSelected(true);
            dessinateur.resetAllSurbrillance();
        }
        else{
            iterItineraire=0;
            listePointsTemp.clear();
            listeCircuitsTemp.clear();
            dessinateur.resetAllSurbrillance();
            chargerModeStandard();
            deselectionBoutons();
        }
    }//GEN-LAST:event_jToggleButtonItineraireActionPerformed

    /**
     * Action du bouton undo
     * @param evt Event
     */
    private void jButtonUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUndoActionPerformed
        undo();
    }//GEN-LAST:event_jButtonUndoActionPerformed

    /**
     * Action du bouton Rapport
     * @param evt Event
     */
    private void jButtonRapportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRapportActionPerformed
        Rapport rapport = new Rapport();
        rapport.setApplication(application);
        if(jComboBoxListeSims.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez sélectionner une simulation", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else{
            rapport.setNomSim(jComboBoxListeSims.getSelectedItem().toString().trim());
            rapport.remplirTableauStat();
            rapport.setVisible(true);
        }
    }//GEN-LAST:event_jButtonRapportActionPerformed

    /**
     * Action sur liste des segments
     * @param evt Event
     */
    private void jComboBoxListeSegmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxListeSegmentsActionPerformed

        //ON VERIFIE SI LA LISTE EST VIDE
        if(jComboBoxListeSegments.getItemCount()<=0){}
        //SI PAS VIDE 
        else if(jComboBoxListeSegments.getSelectedIndex()!=0){
            dessinateur.updateApplication(application);
            if(tempPoint!=null && jComboBoxListeSegments.getItemCount()>0){
                dessinateur.mettreSurbrillanceSegment(tempPoint.getListSegment().get(jComboBoxListeSegments.getSelectedIndex()-1));
                updateCarte();
            }
            if(tempSegment1 != null && jComboBoxListeSegments.getSelectedIndex()==0){
                jComboBoxListeSegments.setSelectedIndex(1);
                jComboBoxListeSegments.setSelectedItem(jComboBoxListeSegments.getItemAt(jComboBoxListeSegments.getSelectedIndex()));
                dessinateur.mettreSurbrillanceSegment(tempSegment1);
                updateCarte();
            }
            if(!listeTempSegment.isEmpty()){
                dessinateur.mettreSurbrillanceSegment(listeTempSegment.get(jComboBoxListeSegments.getSelectedIndex()-1));
                updateCarte();
            }
            
            else{}
        }   
        else{
            dessinateur.resetAllSurbrillance();
            updateCarte();
        }
    }//GEN-LAST:event_jComboBoxListeSegmentsActionPerformed

    /**
     * Action sur les evenements de la liste des circuits (Affichage dans carte)
     * @param evt Event
     */
    private void jComboBoxListeCircuitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxListeCircuitsActionPerformed
        if(jComboBoxListeCircuits.getItemCount()<=1){
        }
        else{
            if(jComboBoxListeCircuits.getSelectedIndex()!=0){
                dessinateur.mettreSurbrillanceCircuit(jComboBoxListeCircuits.getSelectedItem().toString());
                updateCarte();
            }
            else{
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
        }       
    }//GEN-LAST:event_jComboBoxListeCircuitsActionPerformed

    /**
     * Action du boutons modifier Point
     * @param evt Event
     */
    private void jButtonModifierPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierPointActionPerformed
        try{
            tempPoint=application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
            //VERIFIE SI LE POINT EST NULL
            if(tempPoint==null){
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez sélectionner un point à modifier", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            //POINT NON NULL
            else{
                //VERIFIE SI UN RESEAU EST EXISTANT
                if(application.getReseau()==null){
                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun réseau existant","Erreur",JOptionPane.ERROR_MESSAGE);
                }//FIN RESEAU NULL
                //SI OUI, ON DOIT DEMANDER LA SUPPRESSION
                else{
                    //DEMANDE CONFIRMATION MODIFIER NOM POINT
                    int confirmation = JOptionPane.showOptionDialog(null, "Êtes-vous certain de vouloir modifier le nom du point?","Modification de point", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    //SI CONFIRMATION, MODIFIE LE POINT 
                    if(confirmation==JOptionPane.YES_OPTION){
                    //SI LE NOM EST VIDE, ON EFFECTUE LES CHANGEMENTS DIRECTEMENT
                        if(jTextFieldNom.getText().isEmpty()){ 
                            application.modifPoint(tempPoint,jTextFieldNom.getText().trim());
                            dessinateur.updateApplication(application);
                            repaint();
                            JOptionPane.showMessageDialog(new JFrame(), "Changements appliqués avec succès", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                        }
                        //SINON ON VERIFIE S'IL EXISTE UN POINT AVEC CE NOM
                        else{
                            boolean existe = false;
                            //ON VERIFIE CHACUN DES NOMS DES POINTS 
                            for(Point pt : application.getListePoints()){
                                //SI UN POINT EXISTE AVEC CE NOM, ON AFFECTE sortirBoucle À TRUE
                                if(pt.getNom().equals(jTextFieldNom.getText().trim())){
                                    existe = true;
                                }
                                else{}
                            }
                            if(existe == true){
                                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Un point avec ce nom existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                application.modifPoint(tempPoint,jTextFieldNom.getText().trim());
                                dessinateur.updateApplication(application);
                                repaint();
                                JOptionPane.showMessageDialog(new JFrame(), "Changements appliqués avec succès", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                            }
                        }//FIN ELSE NOM POINT NON VIDE
                    }//FIN IF CONFIRMATION = YES
                    //SINON NE FAIT RIEN
                    else{}
                }   
            }
        }
        catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonModifierPointActionPerformed
    
    /**
     * Action du bouton supprimer point
     * @param evt Event
     */
    private void jButtonSupprimerPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerPointActionPerformed
        try{
            tempPoint=application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
            if(tempPoint==null){
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun point sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{
                int confirmation = JOptionPane.showOptionDialog(null, "Êtes-vous certain de vouloir supprimer ce point?","Suppression de point", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(confirmation==JOptionPane.YES_OPTION){
                    if(application.supprPoint(tempPoint).isEmpty()){
                        viderChampsGraphe();
                        dessinateur.updateApplication(application);
                        dessinateur.repaint();
                        JOptionPane.showMessageDialog(new JFrame(), "Point supprimé avec succès", "Confirmation",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        //TODO (Olivier) - Demander confirmation supprimer les circuits passant par ce point et aussi itinéraires le cas échéant
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le point ne peut être supprimé car un circuit passe par celui-ci","Erreur",JOptionPane.ERROR_MESSAGE);   
                    }
                }
                else{
                }
            }
        }
        catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSupprimerPointActionPerformed
    
    /**
     * Action du bouton modifier Segment
     * @param evt Event
     */
    private void jButtonModifierSegmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierSegmentActionPerformed
        listeTempSegment.clear();
        listeTempSegment.addAll(application.verifClicSegment(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))));
        if(listeTempSegment.isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun segment sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(jComboBoxListeSegments.getItemCount()>0){
                modifierSegment();
            }//Fin IF si plus de 0 Items dans liste des semgents
            else{
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun segment sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButtonModifierSegmentActionPerformed
    
    /**
     * Action du bouton supprimer Segment
     * @param evt Event
     */
    private void jButtonSupprimerSegmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerSegmentActionPerformed
        try{
            tempPoint=application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
            //VERIFIE SI AU MOIN 1 SEGMENT DANS LA LISTE
            if(jComboBoxListeSegments.getItemCount()>0 && jComboBoxListeSegments.getSelectedIndex()!=0){
                int indexSelect = jComboBoxListeSegments.getSelectedIndex();
                //SI LE POINT TEMPORARIE N'EST PAS VIDE, C'EST QU'ON A CLIQUÉ SUR UN POINT
                if(tempPoint!=null){
                    //AFFECTE LE SEGMENT TEMPORAIRE AU PREMIER DE LA LISTE DE SEGMENT DU POINT
                    tempSegment1 = tempPoint.getListSegment().get(indexSelect-1);
                }
                //DEMANDE CONFIRMATION
                int confirmation = JOptionPane.showOptionDialog(null, "Êtes-vous certain de vouloir supprimer ce segment?","Suppression de segment", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(confirmation==JOptionPane.YES_OPTION){
                    //SI LE SEGMENT1 EST NULL
                    if(tempSegment1 == null){
                        //ON VERIFIE SI LA LISTE DE SEGMENTS TEMPORAIRE EST VIDE : CLIC DIRECTEMENT SUR SEGMENT PLUTOT QUE VIA POINT
                        if(listeTempSegment.isEmpty()){
                            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Aucun segment sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            //On supprime le segment
                            if(application.supprSeg(listeTempSegment.get(jComboBoxListeSegments.getSelectedIndex()-1)).isEmpty()){   
                                updateCarte();
                                dessinateur.resetAllSurbrillance();
                                chargerModeStandard();
                            }
                            else{
                                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Le segment ne peut être supprimé car un circuit passe par celui-ci","Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    //SI LE SEGMENT TEMPORAIRE N'EST PAS VIDE
                    else{
                        //ON VERIFIE D'ABORD SI LA LISTE DE SEGMENT EST VIDE
                        if(listeTempSegment.isEmpty()){
                            //On supprime le segment
                            if(application.supprSeg(tempSegment1).isEmpty()){   
                                updateCarte();
                                dessinateur.resetAllSurbrillance();
                                chargerModeStandard();
                            }
                            else{
                                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Le segment ne peut être supprimé car un circuit passe par celui-ci","Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        //SI LA LISTE DE SEGMENTS N'EST PAS VIDE
                        else{
                            //On supprime le segment
                            if(application.supprSeg(listeTempSegment.get(jComboBoxListeSegments.getSelectedIndex()-1)).isEmpty()){   
                                dessinateur.resetAllSurbrillance();
                                updateCarte();
                                chargerModeStandard();
                            }
                            else{
                                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Le segment ne peut être supprimé car un circuit passe par celui-ci","Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
                else{
                    dessinateur.resetAllSurbrillance();
                    updateCarte();
                    
                    chargerModeStandard();
                }
            }
            else{
                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Aucun segment sélectionné","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        } 
    }//GEN-LAST:event_jButtonSupprimerSegmentActionPerformed
    
    /**
     * Bouton modifier circuits
     * @param evt Event
     */
    private void jButtonModifierCircuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierCircuitActionPerformed
        modifierCircuit();
    }//GEN-LAST:event_jButtonModifierCircuitActionPerformed

    /**
     * Bouton minimise section détails sélection du menu graphe
     * @param evt Event
     */
    private void jToggleButtonMinimizeDetailsSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeDetailsSelectionActionPerformed
        if(jPanelDetailsSelection.isVisible()==true){
            jPanelDetailsSelection.setVisible(false);
        }
        else{
            jPanelDetailsSelection.setVisible(true);
        }
        
    }//GEN-LAST:event_jToggleButtonMinimizeDetailsSelectionActionPerformed

    /**
     * Bouton minise détails des segments menu graphe
     * @param evt Event
     */
    private void jToggleButtonMinimizeDetailsSegmentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeDetailsSegmentsActionPerformed
        if(jPanelDetailsSegments.isVisible()==true){
            jPanelDetailsSegments.setVisible(false);
        }
        else{
            jPanelDetailsSegments.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButtonMinimizeDetailsSegmentsActionPerformed

    /**
     * Bouton suppression circuit
     * @param evt Event
     */
    private void jButtonSupprimerCircuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerCircuitActionPerformed
        try{
        if(jComboBoxListeCircuits.getItemCount()>1 && jComboBoxListeCircuits.getSelectedIndex()!=0){
            int confirmation = JOptionPane.showOptionDialog(null, "Êtes-vous certain de vouloir supprimer ce circuit?","Suppression de circuit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(confirmation==JOptionPane.YES_OPTION){
                //On supprime le segment
                if(application.supprCircuit(application.getCircuit(jComboBoxListeCircuits.getSelectedItem().toString())).isEmpty()){
                    remplirListeCircuits();
                    dessinateur.resetSurbrillanceSegment();
                    updateCarte();
                }
                else{
                    int confirmation2 = JOptionPane.showOptionDialog(null, "Supprimer ce circuit supprimera le ou les itinéraires le parcourant, continuer?","Suppression de circuit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if(confirmation2 == JOptionPane.YES_OPTION){
                        for(Itineraire it : application.supprCircuit(application.getCircuit(jComboBoxListeCircuits.getSelectedItem().toString()))){
                            application.supprItineraire(it);
                        }
                        application.supprCircuit(application.getCircuit(jComboBoxListeCircuits.getSelectedItem().toString()));
                        remplirListeCircuits();
                        remplirListeItineraire();
                        dessinateur.resetAllSurbrillance();
                        updateCarte();
                    }
                    else{}
                }
            }
            else{
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun circuit à supprimer","Erreur",JOptionPane.ERROR_MESSAGE);
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        } 
        
    }//GEN-LAST:event_jButtonSupprimerCircuitActionPerformed
    
    /**
     * Permet de faire un refresh sur la liste des circuits
     * @param evt Event
     */
    private void jButtonRefreshCircuitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshCircuitsActionPerformed
        remplirListeCircuits();
    }//GEN-LAST:event_jButtonRefreshCircuitsActionPerformed

    /**
     * Minimise les boutons du menu graphe
     * @param evt Event
     */
    private void jToggleButtonMinimizeBoutonsGrapheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeBoutonsGrapheActionPerformed
        if(jPanelBoutonsGraphe.isVisible()==true){
            jPanelBoutonsGraphe.setVisible(false);
        }
        else{
            jPanelBoutonsGraphe.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButtonMinimizeBoutonsGrapheActionPerformed

    /**
     * Minimise la section bouton du menu réseau
     * @param evt Event
     */
    private void jToggleButtonMinimizeBoutonsReseauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeBoutonsReseauActionPerformed
        if(jPanelOutilsReseau.isVisible()==true){
            jPanelOutilsReseau.setVisible(false);
        }
        else{
            jPanelOutilsReseau.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButtonMinimizeBoutonsReseauActionPerformed

    /**
     * Minimise la liste des circuits dans menu réseau
     * @param evt Event
     */
    private void jToggleButtonMinimizeReseauCircActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeReseauCircActionPerformed
        if(jPanelListeCircuits.isVisible()==true){
            jPanelListeCircuits.setVisible(false);
        }
        else{
            jPanelListeCircuits.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButtonMinimizeReseauCircActionPerformed

    /**
     * Minimise la section avec les boutons du menu simulation
     * @param evt Event
     */
    private void jToggleButtonMinimizeBoutonsSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeBoutonsSimulationActionPerformed
        if(jPanelSimulationOutils.isVisible()==true){
            jPanelSimulationOutils.setVisible(false);
        }
        else{
            jPanelSimulationOutils.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButtonMinimizeBoutonsSimulationActionPerformed

    /**
     * Minimise la section parametres de simulation
     * @param evt Event
     */
    private void jButtonMinimiseParamSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinimiseParamSimActionPerformed
        if(jPanelSimulationParametres.isVisible()==true){
            jPanelSimulationParametres.setVisible(false);
        }
        else{
            jPanelSimulationParametres.setVisible(true);
        }
    }//GEN-LAST:event_jButtonMinimiseParamSimActionPerformed

    /**
     * Bouton démarrer simulation
     * @param evt Event
     */
    private void jToggleButtonDemarrerSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDemarrerSimActionPerformed
       if(jToggleButtonDemarrerSim.isSelected()==false){
           jToggleButtonDemarrerSim.setText("Reprendre");
           timer.stop();
           iterSimulation++;
       }
       else{
           //SI AUCUNE SIMULATION
           if(application.getListSim().isEmpty()||jComboBoxListeSims.getSelectedIndex()==0){
               jToggleButtonDemarrerSim.setSelected(false);
               JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucune simulation à démarrer", "Erreur",JOptionPane.ERROR_MESSAGE);
           }
           else{
                chargerModeSimulation();
                jToggleButtonDemarrerSim.setSelected(true);
                jToggleButtonDemarrerSim.setText("Suspendre");
                desactiveChampsSim();
                //ON VERIFIE SI UNE SIMULATION A DEJA ETE DEMARREE
                if(iterSimulation==0){
                    dateCourante=new Date();
                    date1 = (Date)application.getSimParNom(jComboBoxListeSims.getSelectedItem().toString()).getTempsDebSim().clone();
                    date2 = (Date)application.getSimParNom(jComboBoxListeSims.getSelectedItem().toString()).getTempsFinSim().clone();
                    dessinateur.setDateDebutFin(date1, date2);
                    dessinateur.setDateCourante(date1);
                    dessinateur.updateApplication(application);
                    demarrerTimer(date1);
                    iterSimulation++;
                }
                //SI UNE SIMULATION A DEJA ETE DEMARREE, ON REDEMARRE LE TIMER
                else{
                redemarrerTimer();
                }
           } 
        }
    }//GEN-LAST:event_jToggleButtonDemarrerSimActionPerformed

    /**
     * Bouton zoom +
     * @param evt Event
     */
    private void jButtonZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZoomInActionPerformed
         dessinateur.setZoomFactor(1.1*dessinateur.getZoomFactor());
         jLabelZoom.setText("Zoom : " + Math.round(100*(dessinateur.getZoomFactor()))/100d);
         updateCarte();
    }//GEN-LAST:event_jButtonZoomInActionPerformed

    /**
     * Bouton zoom -
     * @param evt Event
     */
    private void jButtonZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZoomOutActionPerformed
        dessinateur.setZoomFactor(dessinateur.getZoomFactor()/1.1);
        jLabelZoom.setText("Zoom : " + Math.round(100*(dessinateur.getZoomFactor()))/100d);
        updateCarte();
    }//GEN-LAST:event_jButtonZoomOutActionPerformed

    /**
     * Permet d'ajuster la vitesse de la simulation
     * @param evt Event
     */
    private void jSliderVitesseSimulationStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderVitesseSimulationStateChanged

        if(jSliderVitesseSimulation.getValue()<0){
            augmenterVitesseTimer(-(long)(1000-(1000*(1/abs((double)jSliderVitesseSimulation.getValue()-1)))));
            jLabelValueSpeed.setText("x" + Integer.toString(jSliderVitesseSimulation.getValue()));
        }
        else if(jSliderVitesseSimulation.getValue()>0){
            augmenterVitesseTimer(1000+(20000*jSliderVitesseSimulation.getValue()));
            jLabelValueSpeed.setText("x" + Integer.toString(jSliderVitesseSimulation.getValue()));
        }
        else if(jSliderVitesseSimulation.getValue()==0){
            augmenterVitesseTimer(0);
            jLabelValueSpeed.setText("x0");
        }
        
    }//GEN-LAST:event_jSliderVitesseSimulationStateChanged
    
    /**
     * Permet d'arreter la simulation
     * @param evt Event
     */
    private void jButtonArreterSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonArreterSimulationActionPerformed
        chargerModeStandard();
        iterSimulation=0;
        jToggleButtonDemarrerSim.setSelected(false);
        jToggleButtonDemarrerSim.setText("Démarrer");
        activeChampsSim();
        if(timer == null){
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucune simulation n'est démarrée", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else{
            dessinateur.resetAllSurbrillance();
            updateCarte();
            arreterTimer();
            dateCourante = new Date();
            jTextFieldHeureCourante.setText("");
            jSliderVitesseSimulation.setValue(0);
        }
    }//GEN-LAST:event_jButtonArreterSimulationActionPerformed

    /**
     * Action lors du deplacement du UI
     * @param evt Event
     */
    private void formComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentMoved
    }//GEN-LAST:event_formComponentMoved

    /**
     * Action lorsque la taille du UI est modifiée
     * @param evt Event
     */
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        carte.setSize(jPanel1.getSize());
        dessinateur.setSizePanel(jPanel1.getSize());
        updateCarte();
    }//GEN-LAST:event_formComponentResized

    /**
     * Action lorsque la taille du jPanel central est modifiée
     * @param evt Event
     */
    private void jPanelCenterComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelCenterComponentResized
        carte.setSize(jPanel1.getSize());
        dessinateur.setSizePanel(jPanel1.getSize());
        updateCarte();
    }//GEN-LAST:event_jPanelCenterComponentResized

    /**
     * Bouton minimise Itineraire
     * @param evt Event
     */
    private void jToggleButtonMinimizeReseauItiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMinimizeReseauItiActionPerformed
        if(jPanelReseauListeIti.isVisible()==true){
            jPanelReseauListeIti.setVisible(false);
        }
        else{
            jPanelReseauListeIti.setVisible(true);
        }
    }//GEN-LAST:event_jToggleButtonMinimizeReseauItiActionPerformed

    /**
     * Bouton refresh liste itineraire
     * @param evt Event
     */
    private void jButtonRefreshListeItiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshListeItiActionPerformed
        remplirListeItineraire();
    }//GEN-LAST:event_jButtonRefreshListeItiActionPerformed

    /**
     * Changement d'onglet menu gauche
     * @param evt 
     */
    private void jTabbedPaneGaucheStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneGaucheStateChanged
        if(jTabbedPaneGauche.getSelectedIndex()==2){
            if(resetSim==false){
                chargerModeSimulation();
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            jComboBoxListeSims.removeAllItems();
            jComboBoxListeSims.addItem("Liste des simulations");
            for(Simulation sim : application.getListSim()){
                jComboBoxListeSims.addItem(sim.getNomSim().trim());
            }
            jComboBoxListeSims.setSelectedIndex(jComboBoxListeSims.getItemCount()-1);
            if(jComboBoxListeSims.getItemCount()>1){
                resetSim = true;
            }
        }
        else{
            if(resetSim==true){
                int confirmation = JOptionPane.showOptionDialog(null, "Si vous continuez, les simulations courantes seront supprimées, continuer?","Supprimer les simulations?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(confirmation == JOptionPane.YES_OPTION){
                    chargerModeStandard();
                    arreterTimer();
                    dessinateur.resetAllSurbrillance();
                    application.flushListSim();
                    updateCarte();
                    resetSim=false;
                    jComboBoxListeSims.removeAllItems();
                }
                else{
                    jTabbedPaneGauche.setSelectedIndex(2);
                }
            }
            else{
                chargerModeStandard();
            }
        }
    }//GEN-LAST:event_jTabbedPaneGaucheStateChanged
    
    /**
     * Bouton créer simulation
     * @param evt Event
     */
    private void jButtonCreerSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreerSimActionPerformed
        Runnable r1 = new Runnable() {
            private volatile boolean stopRequested = false;
            public void run() {
            wait win = new wait();
            win.setAlwaysOnTop(true);
            win.setVisible(true);
            while(!stopRequested){
                launchCreation();
                requestStop();
            }
            win.dispose();
            }
            public void requestStop() {
                stopRequested = true;
            }
        };
        Thread thr1 = new Thread(r1);
        thr1.start(); 
    }//GEN-LAST:event_jButtonCreerSimActionPerformed

    /**
     * Lance la création des simulations
     */
    private void launchCreation(){
        this.setVisible(false);
        ArrayList<Simulation> listeSims = new ArrayList();
        listeSims = application.getListSim();
        if(jTextFieldNomSim.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Le nom de la simulation ne peut être vide", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else if(listeSims.contains(application.getSimParNom(jTextFieldNomSim.getText().trim()))){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : Une simulation avec ce nom existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else{
            date1 = new Date();
            date2 = new Date();
            dateCourante=new Date();
            date1 = (Date)jSpinnerDebutSim.getValue();
            date2 = (Date)jSpinnerFinSim.getValue();
            if(date2.equals(date1)){
                chargerModeStandard();
                jToggleButtonDemarrerSim.setText("Démarrer");
                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : L'heure de fin ne peut être égale à l'heure d'arrivée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else if(date2.before(date1)){
                date2.setTime(date2.getTime()+86400000);
                dessinateur.setDateDebutFin(date1, date2);
                dessinateur.setDateCourante(date1);
                try{
                    if((int)jSpinnerQteSim.getValue()>1){
                        for(int i=0;i<(int)jSpinnerQteSim.getValue();i++){
                            application.CreerSim(date1, date2, jTextFieldNomSim.getText().trim()+(i+1));
                        }
                    }
                    else{
                        application.CreerSim(date1, date2, jTextFieldNomSim.getText().trim());
                    }
                    resetSim=true;
                    dessinateur.updateApplication(application);
                    jComboBoxListeSims.removeAllItems();
                    jComboBoxListeSims.addItem("Liste des Simulations");
                    for(Simulation sim : application.getListSim()){
                        jComboBoxListeSims.addItem(sim.getNomSim());
                    }
                    jComboBoxListeSims.setSelectedIndex(jComboBoxListeSims.getItemCount()-1);
                }catch (uiExcept ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                dessinateur.setDateDebutFin(date1, date2);
                dessinateur.setDateCourante(date1);
                try{
                    if((int)jSpinnerQteSim.getValue()>1){
                        for(int i=0;i<(int)jSpinnerQteSim.getValue();i++){
                            application.CreerSim(date1, date2, jTextFieldNomSim.getText().trim()+(i+1));
                        }
                    }
                    else{
                        application.CreerSim(date1, date2, jTextFieldNomSim.getText().trim());
                    }
                    resetSim=true;
                    dessinateur.updateApplication(application);
                    jComboBoxListeSims.removeAllItems();
                    jComboBoxListeSims.addItem("Liste des Simulations");
                    for(Simulation sim : application.getListSim()){
                        jComboBoxListeSims.addItem(sim.getNomSim());
                    }
                    jComboBoxListeSims.setSelectedIndex(jComboBoxListeSims.getItemCount()-1);
                }catch (uiExcept ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        this.setVisible(true);
    }
    
    /**
     * Menu Fichier - Quitter
     * @param evt Event
     */
    private void jMenuItemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemQuitterActionPerformed
        int confirmation = JOptionPane.showOptionDialog(null, "Quitter l'application?  Les changements non-sauvegardés seront perdus","Quitter?", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(confirmation==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItemQuitterActionPerformed

    /**
     * Menu Fichier - Ouvrir
     * @param evt Event
     */
    private void jMenuOuvrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOuvrirActionPerformed
        chargerSimulation();
    }//GEN-LAST:event_jMenuOuvrirActionPerformed

    /**
     * Menu Fichier - Information
     * @param evt Event 
     */
    private void jMenuItemInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInformationActionPerformed
        JLabel nomProjetLabel = new JLabel("Nom du Projet : ");
        JTextField nomProjetText = new JTextField(nomProjet);
        Object[] objets = {
                nomProjetLabel,
                nomProjetText
            };
            int confirmation = JOptionPane.showOptionDialog(null, objets,"Information projet", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(confirmation==JOptionPane.YES_OPTION){
                if(nomProjetText.getText().isEmpty()){
                    
                }
                else{
                    nomProjet=nomProjetText.getText().trim();
                    jLabelNomProjet.setText("Projet : " + nomProjet);
                }
            }
    }//GEN-LAST:event_jMenuItemInformationActionPerformed

    /**
     * Icone Ouvrir
     * @param evt Event
     */
    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenActionPerformed
            chargerSimulation();
    }//GEN-LAST:event_jButtonOpenActionPerformed

    /**
     * A Propos
     * @param evt Event
     */
    private void jMenuItemAProposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAProposActionPerformed
        JOptionPane.showMessageDialog(null, "SimulatHEURE\nBuild : " + version +"\nDate 2015-11-16","À Propos",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItemAProposActionPerformed

    /**
     * Bouton sauvegarder
     * @param evt 
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        sauvegarderSim();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * Action du bouton Redo
     * @param evt Event
     */
    private void jButtonRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRedoActionPerformed
        redo();
    }//GEN-LAST:event_jButtonRedoActionPerformed

    /**
     * Action du bouton Nouveau
     * @param evt Event
     */
    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        int confirmation = JOptionPane.showOptionDialog(null, "Êtes-vous certain de vouloir créer un nouveau projet?  Touts changements non sauvegardés seront perdus.","Nouveau projet", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(confirmation==JOptionPane.YES_OPTION){
            loadDefaultVarValues();
            chargerModeStandard();
            jLabelNomProjet.setText("Projet : " + nomProjet);
            application = null;
            application = Factory.creerApplication();
            remplirListeCircuits();
            remplirListeItineraire();
            dessinateur.resetAllSurbrillance();
            jTabbedPaneGauche.setSelectedIndex(0);
            updateCarte();
            
        }
    }//GEN-LAST:event_jButtonNewActionPerformed

    /**
     * Bouton déplacement de point
     * @param evt Event
     */
    private void jToggleButtonDeplacerPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDeplacerPointActionPerformed
        if(jToggleButtonDeplacerPoint.isSelected()){
            if(tempPoint!=null){
                deplacerPoint=true;
            }
            else{
                jToggleButtonDeplacerPoint.setSelected(false);
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez sélectionner un point à déplacer", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            deplacerPoint=false;
        }
    }//GEN-LAST:event_jToggleButtonDeplacerPointActionPerformed

    /**
     * Bouton sauvegarder nouveau type de point
     * @param evt Event
     */
    private void jButtonModifierPointTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierPointTypeActionPerformed
        try{
        if(tempPoint!=null){
            ArrayList<Circuit> listeCircuits = application.getCircDunPoint(tempPoint);
            if(listeCircuits.isEmpty()){
                if(tempPoint instanceof Arret){
                    tempPoint = application.changerTypeArretVersIntersection(tempPoint);
                    JOptionPane.showMessageDialog(new JFrame(), "Changement sauvegardé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                    viderChampsGraphe();
                    remplirChampsSelectionPoint();
                }
                else{
                    tempPoint = application.changerTypeIntersectionVersArret(tempPoint);
                    JOptionPane.showMessageDialog(new JFrame(), "Changement sauvegardé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                    viderChampsGraphe();
                    remplirChampsSelectionPoint();
                }
            }
            else{
                int confirmation = JOptionPane.showOptionDialog(null, "La modification supprimera les circuits passants par ce point,  Continuer?","Modification type de point", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(confirmation==JOptionPane.YES_OPTION){
                    for(Circuit circ : listeCircuits){
                        application.supprCircuit(circ);
                    }
                    if(tempPoint instanceof Arret){
                        tempPoint = application.changerTypeArretVersIntersection(tempPoint);
                        JOptionPane.showMessageDialog(new JFrame(), "Changement sauvegardé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                        viderChampsGraphe();
                        remplirChampsSelectionPoint();
                    }
                    else{
                        tempPoint = application.changerTypeIntersectionVersArret(tempPoint);
                        JOptionPane.showMessageDialog(new JFrame(), "Changement sauvegardé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                        viderChampsGraphe();
                        remplirChampsSelectionPoint();
                    }
                    remplirListeCircuits();
                }
                else{}
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez sélectionner un point à modifier", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonModifierPointTypeActionPerformed

    /**
     * Bouton permettant d'éditer la source d'un circuit
     * @param evt Event
     */
    private void jToggleButtonEditerSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonEditerSourceActionPerformed
        if(jToggleButtonEditerSource.isSelected()){
            if(jComboBoxListeCircuits.getItemCount()<=1 || jComboBoxListeCircuits.getSelectedIndex()==0){
                deselectionBoutons();
                jToggleButtonEditerSource.setText("Modifier départ");
                JOptionPane.showMessageDialog(new JFrame(), "Aucun circuit sélectionné", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{
                boolean impossibleModifier = false;
                try{
                for(Itineraire iti:application.getListIti()){
                    for(Circuit circuit : iti.getListCirc()){
                        if(circuit.getNomCircuit().trim().equals(jComboBoxListeCircuits.getSelectedItem().toString().trim())){
                            impossibleModifier = true;
                        }
                    }
                }
                if(impossibleModifier==true){
                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de modifier ce circuit, un itinéraire passe par celui-ci", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    deselectionBoutons();
                    jToggleButtonEditerSource.setSelected(true);
                    jToggleButtonEditerSource.setText("Terminer");
                    modifierSourceCircuit=true;
                    JOptionPane.showMessageDialog(new JFrame(), "Veuillez sélectionner le point source", "Modification point départ",JOptionPane.INFORMATION_MESSAGE);
                }  
                }catch(uiExcept ex){
                    //TODO
                }
                
            }
        }
        else{
            jToggleButtonEditerSource.setText("Modifier départ");
            modifierSourceCircuit=false;
        }        
    }//GEN-LAST:event_jToggleButtonEditerSourceActionPerformed

    /**
     * Action sur la liste des simulations
     * @param evt Event
     */
    private void jComboBoxListeSimsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxListeSimsActionPerformed
        if(jComboBoxListeSims.getItemCount()>1 && jComboBoxListeSims.getSelectedIndex()!=0){
            DateFormat formatDate = new SimpleDateFormat("HH:mm");
            jTextFieldHeureDebut.setText(formatDate.format(application.getSimParNom(jComboBoxListeSims.getSelectedItem().toString().trim()).getTempsDebSim().getTime()));
            jTextFieldHeureFin.setText(formatDate.format(application.getSimParNom(jComboBoxListeSims.getSelectedItem().toString().trim()).getTempsFinSim().getTime()));
        }
        else{
            jTextFieldHeureDebut.setText("");
            jTextFieldHeureFin.setText("");
        }
    }//GEN-LAST:event_jComboBoxListeSimsActionPerformed

    /**
     * Réajustement de la taille de la carte en fonction du panel central
     * @param evt 
     */
    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        carte.setSize(jPanel1.getSize());
    }//GEN-LAST:event_jPanel1ComponentResized

    /**
     * Action du bouton supprimer itineraire
     * @param evt Event
     */
    private void jButtonSupprimerItineraireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerItineraireActionPerformed
        try{
        if(jComboBoxListeItineraire.getItemCount()<=1){
        }
        else{
            if(jComboBoxListeItineraire.getSelectedIndex()!=0){
                application.supprItineraire(application.getIti(jComboBoxListeItineraire.getSelectedItem().toString().trim()));
                remplirListeItineraire();
                updateCarte();
            }
            else{
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
        }  
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSupprimerItineraireActionPerformed

    /**
     * Action sur la liste des itinéraires
     * @param evt Event
     */
    private void jComboBoxListeItineraireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxListeItineraireActionPerformed
        try{
        if(jComboBoxListeItineraire.getItemCount()<=1){
        }
        else{
            if(jComboBoxListeItineraire.getSelectedIndex()!=0){
                dessinateur.resetAllSurbrillance();
                dessinateur.mettreSurbrillanceItineraire(application.getIti(jComboBoxListeItineraire.getSelectedItem().toString().trim()));
                updateCarte();
            }
            else{
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
        }  
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jComboBoxListeItineraireActionPerformed

    /**
     * Action lors clic sur liste de type de carte (affichage arriere plan)
     * @param evt Event
     */
    private void jComboBoxTypeCarteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeCarteActionPerformed
        dessinateur.setBackgroundType(jComboBoxTypeCarte.getSelectedIndex());
        updateCarte();
    }//GEN-LAST:event_jComboBoxTypeCarteActionPerformed

    /**
     * Menu annuler 
     * @param evt Event
     */
    private void jMenuItemAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAnnulerActionPerformed
        undo();
    }//GEN-LAST:event_jMenuItemAnnulerActionPerformed

    /**
     * Menu répéter
     * @param evt Event
     */
    private void jMenuItemRepeterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRepeterActionPerformed
        redo();
    }//GEN-LAST:event_jMenuItemRepeterActionPerformed

    private void jMenuItemEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnregistrerActionPerformed
        sauvegarderSim();
    }//GEN-LAST:event_jMenuItemEnregistrerActionPerformed
      
    //-----------------------
    //CONTRÔLEURS SOURIS
    //-----------------------
    /**
     * ControleurSourisClics
     */
    private class ControleurSourisClics implements MouseListener{
        Point pointDepart;
        Point pointArrivee;
        
        /**
         * Méthode lors du clic
         * @param e Event
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            lastXLocationClic = e.getX();
            lastYLocationClic = jPanel1.getSize().height-e.getY();
        }
        
        /**
         * Méthode lorsque la souris est appuyée seulement
         * @param e Event
         */
        @Override
        public void mousePressed(MouseEvent e) {
            lastXLocationClic = e.getX();
            lastYLocationClic = jPanel1.getSize().height-e.getY();
        }
        
        /**
         * Méthode lorsque la souris est relâchée
         * @param e Event
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            //ACTUALISE LA CARTE
            updateCarte();
            dessinateur.setSizePanel(jPanel1.getSize());

            //-------------------------------
            //CRÉATION DE POINTS
            //-------------------------------
            if(jToggleButtonPoint.isSelected()){
                //Vérifie si point dans range via application
                try{
                    if(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))) == null){
                        //Définition des objets dans le panneau d'option
                        JLabel typePointLabel = new JLabel("Type de point : ");
                        JLabel nomPointLabel = new JLabel("Nom : ");
                        JTextField nomPoint = new JTextField("");
                        JComboBox comboBoxTypePoint = new JComboBox();
                        comboBoxTypePoint.addItem("Arrêt");
                        comboBoxTypePoint.addItem("Intersection");
                        Object[] boutons = {
                            typePointLabel,
                            comboBoxTypePoint,
                            nomPointLabel,
                            nomPoint
                        };
                        //ON DEMANDE CHOIX PARAMETRES DU POINT UN PREMIERE FOIS
                        int confirmation = JOptionPane.showOptionDialog(null, boutons,"Ajout de point", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                        //SI ON CLIC SUR CANCEL, ON ANNULE TOUT
                        if(confirmation == JOptionPane.CANCEL_OPTION){}
                        //SI ON CLIC SUR OK, ON VERIFIE LES DONNEES SAISIES
                        else if(confirmation == JOptionPane.OK_OPTION){
                            //SI LE NOM DU POINT EST VIDE ON L'AJOUTE DIRECTEMENT
                            if(nomPoint.getText().isEmpty()){
                                boolean estUnArret = comboBoxTypePoint.getSelectedItem()=="Arrêt";   
                                application.ajouterPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())),estUnArret, nomPoint.getText());
                                ajoutObjectListeUndo(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))));
                                updateCarte();
                            }
                            else{
                            boolean sortirBoucle;
                            //ON FAIT UNE BOUCLE QUI REDEMANDE LES PARAMETRES S'ILS NE SONT PAS BON
                            while(confirmation == JOptionPane.OK_OPTION){
                                sortirBoucle = false;
                                //VERIFIE S'IL Y A EU UN CANCEL, SI OUI ON SORT DE LA BOUCLE
                                if(confirmation == JOptionPane.CANCEL_OPTION){break;}
                                //SI ON CLIC SUR LE BOUTON OK
                                else if(confirmation == JOptionPane.OK_OPTION){
                                    //ON VERIFIE CHACUN DES NOMS DES POINTS 
                                    for(Point pt : application.getListePoints()){
                                        //SI UN POINT EXISTE AVEC CE NOM, ON AFFECTE sortirBoucle À TRUE
                                        if(pt.getNom().equals(nomPoint.getText().trim())&&pt.getNom().isEmpty()==false){
                                            sortirBoucle = true;
                                        }
                                        //SINON ON NE FAIT RIEN
                                        else{}
                                    }
                                    //ON VERIFIE SI SORTIRBOUCLE EST À TRUE (IL Y A DEJA UN POINT QUI EXISTE, ON REDEMANDE D'AUTRES PARAMETRES
                                    if(sortirBoucle==true){
                                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un point avec ce nom existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                                        confirmation = JOptionPane.showOptionDialog(null, boutons,"Ajout de point", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                    }
                                    //SINON ON PEUT CRÉER LE POINT ET SORTIR DE LA BOUCLE
                                    else{
                                        boolean estUnArret = comboBoxTypePoint.getSelectedItem()=="Arrêt";   
                                        application.ajouterPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()),jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())),estUnArret, nomPoint.getText());
                                        ajoutObjectListeUndo(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))));
                                        updateCarte();
                                        break;
                                    }
                                }
                                //DANS TOUS LES AUTRES CAS C'EST UN ERREUR
                                else{
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : L'option choisie n'est pas valide [Création de points]", "Erreur",JOptionPane.ERROR_MESSAGE);
                                }//FIN ELSE PAS OK NI CANCEL (ERREUR)
                            }//FIN BOUCLE WHILE
                        }//FIN ELSE, NOM POINT NON VIDE
                    }//FIN IF PREMIER CLIC SUR OK
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le point est trop près d'un autre", "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }catch (uiExcept | HeadlessException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }   
            }




            //-------------------------------
            //CRÉATION DE SEGMENTS
            //-------------------------------
            else if(jToggleButtonSegment.isSelected()){
                try{
                    //ITERATEUR A 0 - SELECTION DU PREMIER POINT
                    if(iterSegment==0){
                        if(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())))==null){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le segment doit débuter d'un point, veuillez choisir un point de départ valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            mettreSurbrillancePoint();
                            pointDepart = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                            iterSegment++;
                        }
                    }
                    //ITERATEUR À 1 : SELECTION DU DEUXIEME POINT
                    else if(iterSegment==1){
                        //Pas de point a l'emplacement du clic
                        if(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())))==null){
                            mettreSurbrillancePoint(pointDepart);
                            iterSegment=1;
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le segment doit se terminer sur un point, veuillez sélectionner un point d'arrivé valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                        else if(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))).equals(pointDepart)){
                            iterSegment=1;
                            pointDepart = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                            mettreSurbrillancePoint();
                        }
                        //DEUXIEME POINT VALIDE 
                        else{
                            boolean existeDeja = false;
                            pointArrivee = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                            for(Point pt : application.getListePoints()){
                                if(pt==pointDepart){
                                    for(Segment seg : pt.getListSegment()){
                                        if(seg.getArrivee()==pointArrivee){
                                            existeDeja=true;
                                            iterSegment=0;
                                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un segment relie déjà ces deux points dans cette direction", "Erreur",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                }
                            }
                            if(existeDeja==false){
                                creerSegment(pointDepart, pointArrivee);    
                            }
                        }
                    }
                }
                catch(uiExcept ex){
                    iterSegment=0;
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            
            
            
            //----------------------------------
            //CRÉATION DE CIRCUITS
            //----------------------------------
            //OPTION CIRCUIT
            else if(jToggleButtonCircuit.isSelected()){
                try{
                    //Si iterateur = 0 : Premier point du circuit
                    if((iterReseau==0)&&(!application.getListePoints().isEmpty())){
                        //Recupere le premier point et on s'assure qu'il est valide
                        listePointsTemp.clear();
                        dessinateur.resetAllSurbrillance();
                        updateCarte();
                        pointDepart = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                        if(pointDepart == null){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le circuit doit débuter sur un point, veuillez choisir un point de départ valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                            iterReseau=0;
                        }
                        else if(application.getListSegContigu(pointDepart).isEmpty()){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun segment contigu à partir de ce point", "Erreur",JOptionPane.ERROR_MESSAGE);
                            iterReseau=0;
                        }
                        else{
                            iterReseau++;
                            mettreSurbrillancePointReseauDessinateur(pointDepart);
                            listePointsTemp.add(pointDepart);
                        }
                    }
                    //Si iterateur = 1 : Point suivant du circcuit
                    else if(iterReseau==1){
                        pointArrivee = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                        //Verifie si le point est valide
                        if(pointArrivee==null){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun point à cet emplacement", "Erreur",JOptionPane.ERROR_MESSAGE);
                            mettreSurbrillancePointReseauDessinateur(listePointsTemp.get(listePointsTemp.size()-1));
                        }
                        //ITERATEUR = 1 ET POINT ARRIVE VALIDE
                        else{
                            if(application.getListSegContigu(pointArrivee).isEmpty()&&application.getListePtArr(listePointsTemp.get(listePointsTemp.size()-1)).contains(pointArrivee)){
                                creerCircuit(pointArrivee);
                            }
                            else if(application.getListePtArr(listePointsTemp.get(listePointsTemp.size()-1)).contains(pointArrivee)){
                            //Demande si on termine le circuit
                            int confirmation = JOptionPane.showOptionDialog(null, "Terminer le circuit?","Création circuit", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                            //Si confirmation terminer circuit
                            if(confirmation == JOptionPane.YES_OPTION){
                                creerCircuit(pointArrivee);
                            }
                            //Si ce n'est pas le dernier arrêt du circuit
                            else if(confirmation == JOptionPane.NO_OPTION){
                                listePointsTemp.add(pointArrivee);
                                iterReseau=1;
                                mettreSurbrillancePointReseauDessinateur(pointArrivee);
                            }
                            //Autrement on raffiche les points possible a partir du dernier point ajouté avec succès
                            else{
                                iterReseau=1;
                                mettreSurbrillancePointReseauDessinateur(listePointsTemp.get(listePointsTemp.size()-1));
                            }
                            }
                            else{
                                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de faire poursuivre le circuit à cet emplacement, veuillez réessayer", "Erreur",JOptionPane.ERROR_MESSAGE);
                                mettreSurbrillancePointReseauDessinateur(listePointsTemp.get(listePointsTemp.size()-1));
                            }
                        }
                    }
                    //ITERATEUR A 2, SPECIFIE POINT SOURCE DU CIRCUIT
                    else if(iterReseau==2){
                        tempPoint = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                        if((tempPoint!=null&&boucle==true) || (tempPoint!=null && boucle==false&&tempPoint!=listePointsTemp.get(listePointsTemp.size()-1))){                            
                            if(maxBusReseau>0){
                                ArrayList<Point> tempListePoints = new ArrayList();
                                tempListePoints.addAll(listePointsTemp);
                                application.creerCircuit(nomCircuitModif, boucle, tempListePoints, minReseau, modeReseau, maxReseau, maxBusReseau, tempPoint);
                                ajoutObjectListeUndo(application.getCircuit(nomCircuitModif));
                                updateCarte();
                                remplirListeCircuits(nomCircuitModif.trim());
                                resetVarReseau();
                            }
                            else if(maxBusReseau==0){
                                ArrayList<Point> tempListePoints = new ArrayList();
                                tempListePoints.addAll(listePointsTemp);
                                application.creerCircuit(nomCircuitModif, boucle, tempListePoints, minReseau, modeReseau, maxReseau, tempPoint);
                                ajoutObjectListeUndo(application.getCircuit(nomCircuitModif));
                                updateCarte();
                                remplirListeCircuits(nomCircuitModif.trim());
                                resetVarReseau();
                            }
                            else{
                                resetVarReseau();
                                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, veuillez réessayer", "Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de définir la source à cet emplacement, veuillez réessayer", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        resetVarReseau();
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de créer un circuit à cet emplacement, veuillez recommencer", "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }        
                catch(uiExcept ex){
                    resetVarReseau();
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            
            
            //------------------------------------
            //CRÉATION ITINERAIRE
            //------------------------------------
            else if(jToggleButtonItineraire.isSelected()){
                try{
                    //SI ITERATEUR À 0 - PREMIER POINT DE L'ITINERAIRE
                    if(iterItineraire==0){
                        creerItinerairePremierPoint();
                    }
                    //SI ITERATEUR À 1 - AJOUT D'UN AUTRE ARRET À L'ITINERAIRE EN COURS DE CREATION
                    else if(iterItineraire==1){
                        tempPoint = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                        //AUCUN POINT À L'EMPLACEMENT DU CLIC
                        if(tempPoint==null){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun point à cet emplacement", "Erreur",JOptionPane.ERROR_MESSAGE);
                            dessinateur.mettreSurbrillancePointItineraire(listePointsTemp.get(listePointsTemp.size()-1),listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                            updateCarte();
                        }
                        else if(tempPoint==listePointsTemp.get(listePointsTemp.size()-1)){
                        dessinateur.mettreSurbrillancePointItineraire(tempPoint,listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                        updateCarte();
                        iterItineraire=1;
                        }
                        //IL Y A BIEN UN POINT À L'EMPLACEMENT DU CLIC MAIS AUCUN CIRCUIT
                        else if(tempPoint!=null && application.getCircDunPoint(tempPoint).isEmpty()){
                            dessinateur.mettreSurbrillancePointItineraire(listePointsTemp.get(listePointsTemp.size()-1),listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                            updateCarte();
                            iterItineraire=1;
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun circuit ne circule sur ce point, veuillez modifier votre sélection", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                        else if(tempPoint!=null && application.arretAtteignable(tempPoint)==false && tempPoint instanceof Intersection){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, veuillez créer l'itinéraire à nouveau", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                        else if(tempPoint!=null && application.arretAtteignable(tempPoint)==false && tempPoint instanceof Arret){
                            terminerItineraire();
                        }
                        else if(tempPoint!=null && application.arretAtteignable(tempPoint)==true){
                            terminerItineraire();
                        }
                        else{
                            resetVarItineraire();
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, veuillez créer l'itinéraire à nouveau", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenue lors de la création de l'itinéraire", "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }catch(uiExcept ex){
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
            
            
            
            
            //-------------------------------------
            //AUCUN BOUTON CHOISI (MODE LIBRE)
            //-------------------------------------
            else {
                try{
                if(jTabbedPaneGauche.getSelectedIndex()!=0){
                    if(modifierSourceCircuit==true){
                        tempPoint=null;
                        tempPoint=application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                        Circuit circ = application.getCircuit(jComboBoxListeCircuits.getSelectedItem().toString().trim());
                        application.modifPtSource(circ, tempPoint);
                        JOptionPane.showMessageDialog(new JFrame(), "Source du circuit modifié avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Emplacement non valide", "Erreur",JOptionPane.ERROR_MESSAGE);    
                    }
                }
                else{
                    if(deplacerPoint==true){
                        Point pt = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                        if(pt!=null && application.verifClicPoint(e.getX()*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-((jPanel1.getSize().height-e.getY())-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())))==null){
                            application.modifPositionPoint(pt, e.getX()*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-((jPanel1.getSize().height-e.getY())-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                            updateCarte();
                            viderChampsGraphe();
                            tempPoint = application.verifClicPoint(e.getX()*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-((jPanel1.getSize().height-e.getY())-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                            remplirChampsSelectionPoint();
                        }
                        else{
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible d'effectuer ce déplacement", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                    jTabbedPaneGauche.setSelectedIndex(0);
                    tempPoint = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
                    viderChampsGraphe();
                    //CAS OU IL N'Y A PAS DE POINT
                    if(tempPoint == null){
                        //ON VERIFIE S'IL Y A UN SEGMENT
                        listeTempSegment = new ArrayList(application.verifClicSegment(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))));
                        //S'IL N'Y A PAS DE SEGMENT, ON NE FAIT RIEN
                        if(listeTempSegment.isEmpty()){
                            chargerModeStandard();
                        }
                        //SI LA LISTE DE SEGMENTS N'EST PAS VIDE
                        else{
                            int it=0;
                            dessinateur.updateApplication(application);
                            //ON AFFICHE CHAQUE SEGMENTS DANS LA LISTE DE SEGMENTS ET LES AFFICHE (FAIT PAR L'ACTION SUR LA LISTE)
                            tempSegment1 = listeTempSegment.get(0);
                            dessinateur.mettreSurbrillanceSegment(tempSegment1);
                            jComboBoxListeSegments.addItem("Liste des Segments : ");
                            for(Segment seg : listeTempSegment){
                                if(!seg.getDepart().getNom().isEmpty()&&!seg.getArrivee().getNom().isEmpty()){
                                    jComboBoxListeSegments.addItem(seg.getDepart().getNom() + "->" + seg.getArrivee().getNom());                                    
                                }
                                else{
                                    jComboBoxListeSegments.addItem("Segment " + it);                                    
                                }
                                it++;
                            }
                            jComboBoxListeSegments.setSelectedIndex(1);
                        }
                        
                    }
                    //SI LE POINT N'EST PAS VIDE (IL Y A UN POINT A CET EMPLACEMENT)
                    else{
                        remplirChampsSelectionPoint();
                    }
                }                    
                }
            }catch(uiExcept ex){
                    JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
        }//FIN 
        }
        
        /**
         * Méthode lorsque la souris entre dans la zone de la carte
         * @param e Event
         */
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        
        /**
         * Méthode lorsque la souris sort de la zone
         * @param e Event
         */
        @Override
        public void mouseExited(MouseEvent e) {
            jLabelXCoordonnees.setText("0");
            jLabelYCoordonnees.setText("0");
        }
    }
    
    /**
     * Actions lors de mouvements de souris dans le panneau central
     */
    private class ControleurSourisMouvements implements MouseMotionListener{
        /**
         * Méthode lorsque la souris est cliqué puis glissée
         * @param e Event
         */
        @Override
        public void mouseDragged(MouseEvent e){
        }
        
        /**
         * Méthode lorsque la souris se déplace dans la zone
         * @param e Event
         */
        @Override
        public void mouseMoved(MouseEvent e){
            try{
                Coordonnee coordGeo = Utils.convertPixelToCoord((double)e.getX(),(double)jPanel1.getHeight()-e.getY());
                jLabelXCoordonnees.setText("");
                jLabelXCoordonnees.setText(String.valueOf(coordGeo.getLat()));
                jLabelYCoordonnees.setText("");
                jLabelYCoordonnees.setText(String.valueOf(coordGeo.getLon()));
            }catch(SimulExcept ex){
                
            }   
        }        
    }
    
    /**
     * Controleur roulette souris
     */
    private class ControleurRouletteSouris implements MouseWheelListener{
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            //Zoom in
            if(e.getWheelRotation()<0){
                dessinateur.setZoomFactor(1.1*dessinateur.getZoomFactor());
                jLabelZoom.setText("Zoom : " + Math.round(100*(dessinateur.getZoomFactor()))/100d);
                updateCarte();
            }
            //Zoom out
            if(e.getWheelRotation()>0){
                dessinateur.setZoomFactor(dessinateur.getZoomFactor()/1.1);
                jLabelZoom.setText("Zoom : " + Math.round(100*(dessinateur.getZoomFactor()))/100d);
                updateCarte();
            }
        }
        
    }
    
    
    
    //-----------------------
    //TIMER
    //-----------------------
    
     /**
     * Action lorsque le timer est appelé
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            dateCourante.setTime(dateCourante.getTime()+timerAjust);
            if(dateCourante.getTime()>=date2.getTime()){
                dateCourante.setTime(date2.getTime());
                dessinateur.setDateCourante(dateCourante);
                arreterTimer();
                iterFinSim=true;
            }
            else{
                dessinateur.setDateCourante(dateCourante);
                application.majListsPassagers(dateCourante,application.getListSim().get(jComboBoxListeSims.getSelectedIndex()-1).getNomSim());
            }
            updateHorloge();
            updateCarte();
            if(iterFinSim==true){
                chargerModeStandard();
                arreterTimer();
                iterFinSim=false;
                JOptionPane.showMessageDialog(null,"Simulation terminée");
                Rapport rapport = new Rapport();
                rapport.setApplication(application);
                if(jComboBoxListeSims.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez sélectionner une simulation", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    rapport.setNomSim(jComboBoxListeSims.getSelectedItem().toString().trim());
                    rapport.remplirTableauStat();
                    rapport.setVisible(true);
                }
            }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Permet de démarrer le timer de la simulation
     * @param dateDebut Date de debut de la simulation
     * @param dateFin Date de fin de la simulation
     */
    private void demarrerTimer(Date dateDebut){
        dessinateur.setAfficherBus(jComboBoxListeSims.getSelectedItem().toString().trim());
        dateCourante = (Date)dateDebut.clone();
        timer = new Timer(1000, this);
        timer.start();
    }
    
    /**
     * Permet d'arrêter le timer
     */
    private void arreterTimer(){
        if(timer==null){}
        else{
            timer.stop();
            timer.removeActionListener(this);
        }
    }
    
    /**
     * Permet de rédémarrer le timer (après pause)
     */
    private void redemarrerTimer(){
        timer.restart();
    }
    
    /**
     * Permet d'augmenter la vitesse de la simulation
     * @param facteur Facteur d'augmentation 
     */
    private void augmenterVitesseTimer(long facteur){
        timerAjust = 1000+facteur;
    }
        
    /**
     * Permet de mettre à jour l'horloge (heure actuelle de simulation)
     */
    private void updateHorloge(){
        DateFormat format = new SimpleDateFormat("HH:mm");
        String heureCouranteString = format.format(dateCourante);
        jTextFieldHeureCourante.setText(heureCouranteString);
    }
    
    
    
    
    //--------------------------
    //CRÉATION / MODIFICATION / SUPPRESSION OBJETS / MISES-À-JOURS LISTES
    //--------------------------
    
    
    /**
     * Permet de créer un segment à partir d'un point de départ et point arrivée valide
     * @param dep Point de depart
     * @param arr Point d'arrivée
     */
    private void creerSegment(Point dep, Point arr){
        //Définition des objets dans le panneau d'option
        JLabel minSegmentTitre = new JLabel("Temps minimal [minutes] : ");
        JLabel modeSegmentTitre = new JLabel("Temps modal [minutes] : ");
        JLabel maxSegmentTitre = new JLabel("Temps maximal [minutes] : ");
        SpinnerModel model1 = new SpinnerNumberModel(3.0,1.0,99999999.0,0.1);
        SpinnerModel model2 = new SpinnerNumberModel(5.0,1.0,99999999.0,0.1);
        SpinnerModel model3 = new SpinnerNumberModel(10.0,1.0,99999999.0,0.1);
        JSpinner minSegment = new JSpinner(model1);
        JSpinner modeSegment = new JSpinner(model2);
        JSpinner maxSegment = new JSpinner(model3);
        JFormattedTextField tfMinSegment = ((JSpinner.DefaultEditor) minSegment.getEditor()).getTextField();
        tfMinSegment.setEditable(false);
        JFormattedTextField tfModeSegment = ((JSpinner.DefaultEditor) modeSegment.getEditor()).getTextField();
        tfModeSegment.setEditable(false);
        JFormattedTextField tfMaxSegment = ((JSpinner.DefaultEditor) maxSegment.getEditor()).getTextField();
        tfMaxSegment.setEditable(false);
        Object[] boutons = {
            minSegmentTitre,
            minSegment,
            modeSegmentTitre,
            modeSegment,
            maxSegmentTitre,
            maxSegment
            };
        int confirmation = JOptionPane.showOptionDialog(null, boutons,"Ajout de segment", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
        while(confirmation==JOptionPane.OK_OPTION){
            //ON VALIDE LES DONNÉES DE MIN MODE ET MAX
            if(confirmation == JOptionPane.OK_OPTION){
                if((Double)minSegment.getValue()>(Double)modeSegment.getValue() || (Double)modeSegment.getValue()>(Double)maxSegment.getValue() || (Double)minSegment.getValue()>(Double)maxSegment.getValue()){
                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez saisir des données valide (Min<=Mode<=Max)", "Erreur",JOptionPane.ERROR_MESSAGE);
                    confirmation = JOptionPane.showOptionDialog(null, boutons,"Ajout de segment", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                }
                else{
                    try{
                        application.ajouterSegment(dep,arr, Double.parseDouble(minSegment.getValue().toString()), Double.parseDouble(modeSegment.getValue().toString()), Double.parseDouble(maxSegment.getValue().toString()));
                        ajoutObjectListeUndo(application.verifClicSegment(dep.getCoordPixels().lat, dep.getCoordPixels().lon).get(application.verifClicSegment(dep.getCoordPixels().lat, dep.getCoordPixels().lon).size()-1));
                        updateCarte();
                        break;
                    }catch(uiExcept ex){
                        JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                    updateCarte();
                    iterSegment=0;
                }
            }
            else{
                iterSegment=0;
                break;
            }
        }
        iterSegment = 0;   
    }
    
    /**
     * Permet de créer un circuit - Instance à lancé suite au clic du deuxième point
     * @param pointArrivee : Point d'arrivé du circuit
     */
    private void creerCircuit(Point pointArrivee){
        int boucleCirc=JOptionPane.NO_OPTION;
        if(pointArrivee == listePointsTemp.get(0)){
            boucleCirc = JOptionPane.showOptionDialog(null, "Voulez-vous créer une boucle?","Spécifications du circuit", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
        }
        if(boucleCirc==JOptionPane.YES_OPTION){
            boucle=true;
        }
        try{
            JLabel nomCircuit = new JLabel("Nom du circuit : ");
            JTextField nomCircuitText = new JTextField("");
            JLabel pointSourceLabel = new JLabel("Modifier point source des autobus?");
            JCheckBox checkBoxSource = new JCheckBox();
            JLabel maxBus = new JLabel("Nombre total de bus simultané [0 si infini] : ");
            SpinnerModel model1 = new SpinnerNumberModel(0,0,100,1);
            SpinnerModel model2 = new SpinnerNumberModel(15.0,1.0,99999999.0,0.1);
            SpinnerModel model3 = new SpinnerNumberModel(15.0,1.0,99999999.0,0.1);
            SpinnerModel model4 = new SpinnerNumberModel(15.0,1.0,99999999.0,0.1);
            JSpinner maxBusSpin = new JSpinner(model1);
            JLabel minCircuit = new JLabel("Temps minimal entre apparition véhicules [minutes] : ");
            JSpinner minCircuitText = new JSpinner(model2);
            JLabel modeCircuit = new JLabel("Temps modal entre apparition véhicules [minutes] : ");
            JSpinner modeCircuitText = new JSpinner(model3);
            JLabel maxCircuit = new JLabel("Temps maximal entre apparition véhicules [minutes] : ");
            JSpinner maxCircuitText = new JSpinner(model4);
            JFormattedTextField tfMaxBusCircuit = ((JSpinner.DefaultEditor) maxBusSpin.getEditor()).getTextField();
            tfMaxBusCircuit.setEditable(false);
            JFormattedTextField tfMinCircuit = ((JSpinner.DefaultEditor) minCircuitText.getEditor()).getTextField();
            tfMinCircuit.setEditable(false);
            JFormattedTextField tfModeCircuit = ((JSpinner.DefaultEditor) modeCircuitText.getEditor()).getTextField();
            tfModeCircuit.setEditable(false);
            JFormattedTextField tfMaxCircuit = ((JSpinner.DefaultEditor) maxCircuitText.getEditor()).getTextField();
            tfMaxCircuit.setEditable(false);                                
            Object[] boutons2 = {
                nomCircuit,
                nomCircuitText,
                maxBus,
                maxBusSpin,
                minCircuit,
                minCircuitText,
                modeCircuit,
                modeCircuitText,
                maxCircuit,
                maxCircuitText,
                pointSourceLabel,
                checkBoxSource                                
            };
            int confirmation2 = JOptionPane.showOptionDialog(null, boutons2,"Spécifications du circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(confirmation2 == JOptionPane.CANCEL_OPTION){
                resetVarReseau();
                updateCarte();
            }
            //SI ON CLIQUE SUR OK
            else if(confirmation2 == JOptionPane.OK_OPTION){
                while(confirmation2==JOptionPane.OK_OPTION){
                    ArrayList<Circuit> temp = new ArrayList();
                    temp.addAll(application.getListCircuit());
                    if(nomCircuitText.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le nom du circuit ne peut être vide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        confirmation2 = JOptionPane.showOptionDialog(null, boutons2,"Spécifications du circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    }
                    else if(temp.contains(application.getCircuit(nomCircuitText.getText().trim().toLowerCase()))){
                           JOptionPane.showMessageDialog(new JFrame(), "Erreur : Ce nom de circuit existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                           confirmation2 = JOptionPane.showOptionDialog(null, boutons2,"Spécifications du circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                        
                    }
                    else if(Double.parseDouble(minCircuitText.getValue().toString())>Double.parseDouble(modeCircuitText.getValue().toString())||
                            Double.parseDouble(modeCircuitText.getValue().toString())>Double.parseDouble(maxCircuitText.getValue().toString())||
                            Double.parseDouble(minCircuitText.getValue().toString())>Double.parseDouble(maxCircuitText.getValue().toString())){
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Données min/mode/max non valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        confirmation2 = JOptionPane.showOptionDialog(null, boutons2,"Spécifications du circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    }
                    else{
                        //Ajoute le point suivant a la liste
                        listePointsTemp.add(pointArrivee);
                        //NB BUS MAX INFINI
                        if((int)maxBusSpin.getValue()==0){
                            if(checkBoxSource.isSelected()==true){
                                int choixSource = JOptionPane.showOptionDialog(null, "Veuillez sélectionner le point source","Création circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                if(choixSource == JOptionPane.OK_OPTION){
                                    nomCircuitModif = nomCircuitText.getText().trim().toLowerCase();
                                    minReseau = Double.parseDouble(minCircuitText.getValue().toString());
                                    modeReseau =  Double.parseDouble(modeCircuitText.getValue().toString());
                                    maxReseau = Double.parseDouble(maxCircuitText.getValue().toString());
                                    maxBusReseau = 0;
                                    iterReseau=2;
                                }
                                else{
                                    resetVarReseau();
                                }
                                break;
                            }
                            //Sinon on affiche le circuit et remet l'iterateur a 0
                            else if(checkBoxSource.isSelected()==false){
                                ArrayList<Point> tempListePoints = new ArrayList();
                                tempListePoints.addAll(listePointsTemp);
                                listePointsTemp.clear();
                                application.creerCircuit(nomCircuitText.getText().trim().toLowerCase(), boucle, tempListePoints,
                                        Double.parseDouble(minCircuitText.getValue().toString()),
                                        Double.parseDouble(modeCircuitText.getValue().toString()),
                                        Double.parseDouble(maxCircuitText.getValue().toString()),
                                        tempListePoints.get(0));
                                ajoutObjectListeUndo(application.getCircuit(nomCircuitText.getText().trim().toLowerCase()));
                                updateCarte();
                                remplirListeCircuits(nomCircuitText.getText().trim());
                                resetVarReseau();
                                JOptionPane.showMessageDialog(new JFrame(), "Circuit créé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            else{
                                resetVarReseau();
                                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenue", "Erreur",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }//FIN IF SPINNER NB MAX BUS == 0
                        //NB BUS MAX DEFINI
                        else if((int)maxBusSpin.getValue()>0){
                            //CHECK BOX SOURCE SELECTIONNEE
                            if(checkBoxSource.isSelected()==true){
                                int choixSource = JOptionPane.showOptionDialog(null, "Veuillez sélectionner le point source","Création circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                if(choixSource == JOptionPane.OK_OPTION){
                                    nomCircuitModif = nomCircuitText.getText().trim().toLowerCase();
                                    minReseau = Double.parseDouble(minCircuitText.getValue().toString());
                                    modeReseau =  Double.parseDouble(modeCircuitText.getValue().toString());
                                    maxReseau = Double.parseDouble(maxCircuitText.getValue().toString());
                                    maxBusReseau = 0;
                                    iterReseau=2;
                                }
                                else{
                                    resetVarReseau();
                                }
                                break;
                            }
                            //CHECK BOX SOURCE PAS SELECTIONNEE
                            else if(checkBoxSource.isSelected()==false){
                                ArrayList<Point> tempListePoints = new ArrayList();
                                tempListePoints.addAll(listePointsTemp);
                                listePointsTemp.clear();
                                application.creerCircuit(nomCircuitText.getText().trim().toLowerCase(), boucle, tempListePoints,
                                        Double.parseDouble(minCircuitText.getValue().toString()),
                                        Double.parseDouble(modeCircuitText.getValue().toString()),
                                        Double.parseDouble(maxCircuitText.getValue().toString()),
                                        Integer.parseInt(maxBusSpin.getValue().toString()),
                                        tempListePoints.get(0));
                                updateCarte();
                                ajoutObjectListeUndo(application.getCircuit(nomCircuitText.getText().trim().toLowerCase()));
                                remplirListeCircuits(nomCircuitText.getText().trim());
                                resetVarReseau();
                                JOptionPane.showMessageDialog(new JFrame(), "Circuit créé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            //AUTREMENT, AFFICHE ERREUR ET RESET DES VARIABLES
                            else{
                                resetVarReseau();
                                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenue", "Erreur",JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                        }//FIN IF NB BUS MAX DEFINI
                    }//FIN ELSE - NOM CIRCUIT VALIDE
                }//FIN BOUCLE WHILE
            }//FIN IF CLIC SUR OK
            //DANS TOUS LES AUTRES CAS, LANCE ERREUR
            else{
                resetVarReseau();
                updateCarte();
            }
        }catch(uiExcept ex){
            resetVarReseau();
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Exécution lors du premier clic de la création d'un itinéraire
     */
    private void creerItinerairePremierPoint(){
        try{
        //S'ASSURE QUE LES VARIABLES SONT DANS L'ÉTAT INITIAL
        resetVarItineraire();
        //RECUPERE L'EMPLACEMENT DU CLIC
        tempPoint = application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor())));
        //SI AUCUN POINT
        if(tempPoint==null){
            resetVarItineraire();
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun point à cet emplacement", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        //SI POINT PAS UN ARRET
        else if(tempPoint instanceof Intersection){
            resetVarItineraire();
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de débuter d'une intersection", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        //SI POINT EST UN ARRET - ON PEUT DÉBUTER LA CRÉATION DE L'ITINERAIRE
        else if(tempPoint instanceof Arret){
            listePointsTemp.clear();
            //AUCUN CIRCUIT PASSANT PAR CET ARRÊT
            if(application.getCircDunPoint(tempPoint).size()<=0){
                resetVarItineraire();
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun circuit ne circule sur cet arrêt, veuillez modifier votre sélection", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            //SEULEMENT 1 CIRCUIT
            else if(application.getCircDunPoint(tempPoint).size()==1 && application.getProchPtPossibleIti(tempPoint,application.getCircDunPoint(tempPoint).get(0))!=null){
                iterItineraire=1;
                listePointsTemp.add(tempPoint);
                listeCircuitsTemp.addAll(application.getCircDunPoint(tempPoint));
                dessinateur.resetAllSurbrillance();
                dessinateur.mettreSurbrillancePointItineraire(tempPoint,listeCircuitsTemp.get(0));
                updateCarte();
            }
            //PLUS D'UN CIRCUIT
            else if(application.getCircDunPoint(tempPoint).size()>1 && application.arretAtteignable(tempPoint)){
                choisirCircuitItineraire();
            }
            else{
                resetVarItineraire();
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de créer un itinéraire à partir de cet emplacement", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
        //DANS TOUS LES AUTRES CAS, ON DEMANDE DE RECOMMENCER
        else{
            iterItineraire=0;
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, veuillez réessayer", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Permet d'afficher le menu de choix du circuit s'il y en a plusieurs sur le point sélectionné lors de la création d'itinéraire
     */
    private void choisirCircuitItineraire(){
        try{
            JLabel choixCircuitLabel = new JLabel("Veuillez choisir le circuit à emprunter : ");
            JComboBox listeCircuits = new JComboBox();
            application.getCircDunPoint(tempPoint).stream().forEach((circ)->{listeCircuits.addItem(circ.getNomCircuit().trim().toLowerCase());});
            Object[] fields = {
                choixCircuitLabel,
                listeCircuits
            };
        int confirmationCircuit = JOptionPane.showOptionDialog(null, fields,"Choix du circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
        if(confirmationCircuit == JOptionPane.OK_OPTION){
            ajouterCircuitAListe(application.getCircuit(listeCircuits.getSelectedItem().toString().trim().toLowerCase()));
            listePointsTemp.add(tempPoint);
            dessinateur.resetAllSurbrillance();
            dessinateur.mettreSurbrillancePointItineraire(tempPoint, application.getCircuit(listeCircuits.getSelectedItem().toString().trim().toLowerCase()));
            updateCarte();
            iterItineraire=1;
        }
        else{
            resetVarItineraire();
            dessinateur.resetAllSurbrillance();
            updateCarte();
        }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : "+ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Permet de créer un itineraire
     */
    private void terminerItineraire(){
        try{
            //VERIFIE SI AUCUN AUTRE POINT POSSIBLE ET POINT COURANT EST UN ARRET - TERMINE LE CIRCUIT AUTOMATIQUEMENT
            if(application.getProchPtPossibleIti(tempPoint, listeCircuitsTemp.get(listeCircuitsTemp.size()-1))==null && tempPoint instanceof Arret && application.getCircDunPoint(tempPoint).size()==1){
                    JLabel nomItineraireLabel = new JLabel("Nom de l'itinéraire : ");
                    JTextField nomItineraire = new JTextField("");
                    SpinnerModel model0 = new SpinnerNumberModel(0.0,0.0,99999999.0,0.1);
                    SpinnerModel model1 = new SpinnerNumberModel(0.0,0.0,99999999.0,0.1);
                    SpinnerModel model2 = new SpinnerNumberModel(1.0,0.0,99999999.0,0.1);
                    SpinnerModel model3 = new SpinnerNumberModel(5.0,0.0,99999999.0,0.1);
                    JLabel tempApparition = new JLabel("Temps apparition premier passager [minutes] : ");
                    JSpinner tempApparitionText = new JSpinner(model0);
                    JLabel minItineraire = new JLabel("Temps minimal entre apparition passagers [minutes] : ");
                    JSpinner minItineraireText = new JSpinner(model1);
                    JLabel modeItineraire = new JLabel("Temps modal entre apparition passagers [minutes] : ");
                    JSpinner modeItineraireText = new JSpinner(model2);
                    JLabel maxItineraire = new JLabel("Temps maximal entre apparition passagers [minutes] : ");
                    JSpinner maxItineraireText = new JSpinner(model3);
                    JFormattedTextField tfMaxPassIti = ((JSpinner.DefaultEditor) maxItineraireText.getEditor()).getTextField();
                    tfMaxPassIti.setEditable(false);
                    JFormattedTextField tfMinPassIti = ((JSpinner.DefaultEditor) minItineraireText.getEditor()).getTextField();
                    tfMinPassIti.setEditable(false);
                    JFormattedTextField tfModePassIti = ((JSpinner.DefaultEditor) modeItineraireText.getEditor()).getTextField();
                    tfModePassIti.setEditable(false);
                    Object[] fields = {
                    nomItineraireLabel,
                    nomItineraire,
                    tempApparition,
                    tempApparitionText,
                    minItineraire,
                    minItineraireText,
                    modeItineraire,
                    modeItineraireText,
                    maxItineraire,
                    maxItineraireText
                    };
                    int confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    while(confirmationNomIti==JOptionPane.OK_OPTION){
                        ArrayList<Itineraire> listeDesIti = application.getListIti();
                        if(nomItineraire.getText().trim().isEmpty()){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le nom de l'itinéraire ne peut être vide", "Erreur",JOptionPane.ERROR_MESSAGE);
                            confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                        }
                        else{
                            if(confirmationNomIti==JOptionPane.OK_OPTION){
                                boolean existeDeja=false;
                                for(Itineraire iti : listeDesIti){
                                    if(iti.getNomIti().equals(nomItineraire.getText().trim().toLowerCase())){
                                        existeDeja=true;
                                    }
                                }
                                if(existeDeja==true){
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Ce nom de d'itinéraire existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                                    confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                else if(Double.parseDouble(minItineraireText.getValue().toString())>Double.parseDouble(modeItineraireText.getValue().toString())||
                                    Double.parseDouble(modeItineraireText.getValue().toString())>Double.parseDouble(maxItineraireText.getValue().toString())||
                                    Double.parseDouble(minItineraireText.getValue().toString())>Double.parseDouble(maxItineraireText.getValue().toString())){
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Données min/mode/max non valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                                    confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                else{
                                    if(tempPoint!=listePointsTemp.get(listePointsTemp.size()-1)){
                                        listePointsTemp.add(tempPoint);
                                    }
                                    application.CreerItineraire(listePointsTemp, listeCircuitsTemp, listePivotsTemp,nomItineraire.getText().trim().toLowerCase(),
                                        (Double)tempApparitionText.getValue(),
                                        (Double)minItineraireText.getValue(),
                                        (Double)modeItineraireText.getValue(),
                                        (Double)maxItineraireText.getValue());
                                    JOptionPane.showMessageDialog(new JFrame(), "Itinéraire créé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                                    remplirListeItineraire(nomItineraire.getText().trim().toLowerCase());
                                    ajoutObjectListeUndo(application.getIti(nomItineraire.getText().trim().toLowerCase()));
                                    resetVarItineraire();
                                    break;
                                }
                            }
                            else{
                                resetVarItineraire();
                                break;
                            }
                        
                        }
                    }
                    resetVarItineraire();                
            }//FIN IF - AUCUN AUTRE POINT POSSIBLE ITINERAIRE ET POINT = ARRET
            
            //AUCUN POINT POSSIBLE SUIVANTE ET LE POINT COURANT EST UNE INTERSECTION - CE CAS NE DEVRAIT PAS ÊTRE LEVÉ EN TEMP NORMAL
            else if((application.getProchPtPossibleIti(tempPoint, listeCircuitsTemp.get(listeCircuitsTemp.size()-1))==null||application.arretAtteignable(tempPoint)==false) && tempPoint instanceof Intersection){
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Impossible de poursuivre l'itinéraire sur cet intersection, veuillez choisir un autre point ou recommencer", "Erreur",JOptionPane.ERROR_MESSAGE);
                dessinateur.mettreSurbrillancePointItineraire(listePointsTemp.get(listePointsTemp.size()-1),listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                updateCarte();
                iterItineraire=1;
            }
            
            //DANS TOUS LES AUTRES CAS
            else{
                int confirmation = JOptionPane.NO_OPTION;
                //SI POINT = ARRET - ON DEMANDE SI ON TERMINE L'ITINERAIRE
                if(tempPoint instanceof Arret){
                    confirmation = JOptionPane.showOptionDialog(null, "Terminer l'itinéraire?","Création d'itinéraire", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                }            
                //SI CONFIRMATION ET POINT EST UN ARRET - DEMANDE TERMINER ITINERAIRE
                if(confirmation == JOptionPane.YES_OPTION && tempPoint instanceof Arret){
                    JLabel nomItineraireLabel = new JLabel("Nom de l'itinéraire : ");
                    JTextField nomItineraire = new JTextField("");
                    SpinnerModel model0 = new SpinnerNumberModel(0.0,0.0,50.0,0.1);
                    SpinnerModel model1 = new SpinnerNumberModel(0.0,0.0,50.0,0.1);
                    SpinnerModel model2 = new SpinnerNumberModel(1.0,0.0,50.0,0.1);
                    SpinnerModel model3 = new SpinnerNumberModel(5.0,0.0,50.0,0.1);
                    JLabel tempApparition = new JLabel("Temps apparition premier passager [minutes] : ");
                    JSpinner tempApparitionText = new JSpinner(model0);
                    JLabel minItineraire = new JLabel("Temps minimal entre apparition passagers [minutes] : ");
                    JSpinner minItineraireText = new JSpinner(model1);
                    JLabel modeItineraire = new JLabel("Temps modal entre apparition passagers [minutes] : ");
                    JSpinner modeItineraireText = new JSpinner(model2);
                    JLabel maxItineraire = new JLabel("Temps maximal entre apparition passagers [minutes] : ");
                    JSpinner maxItineraireText = new JSpinner(model3);
                    JFormattedTextField tfMaxPassIti = ((JSpinner.DefaultEditor) maxItineraireText.getEditor()).getTextField();
                    tfMaxPassIti.setEditable(false);
                    JFormattedTextField tfMinPassIti = ((JSpinner.DefaultEditor) minItineraireText.getEditor()).getTextField();
                    tfMinPassIti.setEditable(false);
                    JFormattedTextField tfModePassIti = ((JSpinner.DefaultEditor) modeItineraireText.getEditor()).getTextField();
                    tfModePassIti.setEditable(false);
                    Object[] fields = {
                        nomItineraireLabel,
                        nomItineraire,
                        tempApparition,
                        tempApparitionText,
                        minItineraire,
                        minItineraireText,
                        modeItineraire,
                        modeItineraireText,
                        maxItineraire,
                        maxItineraireText
                    };
                    int confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    while(confirmationNomIti==JOptionPane.OK_OPTION){
                        ArrayList<Itineraire> listeDesIti = application.getListIti();
                        if(nomItineraire.getText().trim().isEmpty()){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le nom de l'itinéraire ne peut être vide", "Erreur",JOptionPane.ERROR_MESSAGE);
                            confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                        }
                        else{
                            if(confirmationNomIti==JOptionPane.OK_OPTION){
                                boolean existeDeja=false;
                                for(Itineraire iti : listeDesIti){
                                    if(iti.getNomIti().equals(nomItineraire.getText().trim().toLowerCase())){
                                        existeDeja=true;
                                    }
                                }
                                if(existeDeja==true){
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Ce nom de d'itinéraire existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                                    confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                else if(Double.parseDouble(minItineraireText.getValue().toString())>Double.parseDouble(modeItineraireText.getValue().toString())||
                                    Double.parseDouble(modeItineraireText.getValue().toString())>Double.parseDouble(maxItineraireText.getValue().toString())||
                                    Double.parseDouble(minItineraireText.getValue().toString())>Double.parseDouble(maxItineraireText.getValue().toString())){
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Données min/mode/max non valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                                    confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                else{
                                    if(tempPoint!=listePointsTemp.get(listePointsTemp.size()-1)){
                                        listePointsTemp.add(tempPoint);
                                    }
                                    application.CreerItineraire(listePointsTemp, listeCircuitsTemp, listePivotsTemp,nomItineraire.getText().trim().toLowerCase(),
                                        (Double)tempApparitionText.getValue(),
                                        (Double)minItineraireText.getValue(),
                                        (Double)modeItineraireText.getValue(),
                                        (Double)maxItineraireText.getValue());
                                    JOptionPane.showMessageDialog(new JFrame(), "Itinéraire créé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                                    remplirListeItineraire(nomItineraire.getText().trim().toLowerCase());
                                    ajoutObjectListeUndo(application.getIti(nomItineraire.getText().trim().toLowerCase()));
                                    resetVarItineraire();
                                    break;
                                }
                            }
                            else{
                                resetVarItineraire();
                                break;
                            }
                        
                        }
                    }
                    resetVarItineraire();
                }
                //SI CONFIRMATION MAIS POINT EST UN INTERSECTION
                else if (confirmation == JOptionPane.YES_OPTION && tempPoint instanceof Intersection){
                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : L'itinéraire ne peut se terminer sur une intersection", "Erreur",JOptionPane.ERROR_MESSAGE);
                    dessinateur.mettreSurbrillancePointItineraire(listePointsTemp.get(listePointsTemp.size()-1),listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                    updateCarte();
                    iterItineraire=1;
                }
                else if(confirmation == JOptionPane.NO_OPTION && tempPoint instanceof Intersection){
                    ArrayList<Circuit> listeCircIntersection = application.getCircDunPoint(tempPoint);
                    //AUCUN CIRCUIT                    
                    if(listeCircIntersection.size()<=0){
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, aucun circuit ne passe par cet intersection", "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                    //UN SEUL CIRCUIT
                    else if(listeCircIntersection.size()==1){
                        //SI NOUS SOMMES SUR LE MEME CIRCUIT QUE LE PRÉCÉDENT
                        if(listeCircIntersection.get(0).equals(listeCircuitsTemp.get(listeCircuitsTemp.size()-1))){
                            //SI ON A UNE BOUCLE EN ALLEZ SIMPLE ET QUE NOUS SOMMES SUR LE DERNIER POINT DE CE CIRCUIT
                            if(listeCircIntersection.get(0) instanceof AllerSimple && listeCircIntersection.get(0).getCircdepuisSource().get(0)!=listeCircIntersection.get(0).getCircdepuisSource().get(listeCircIntersection.get(0).getCircdepuisSource().size()-1)&&tempPoint==listeCircIntersection.get(0).getSource()){
                                resetVarItineraire();
                                dessinateur.resetAllSurbrillance();
                                updateCarte();
                                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, le circuit courant se termine sur cet intersection, impossible de débarquer d'un autobus à cet emplacement", "Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                ArrayList<Point> listePoints = application.getListePoints();
                                ArrayList<Segment> seg = new ArrayList();
                                boolean pointDepartValide=false;
                                boolean pointArriveeValide=false;
                                for(Point pt : listePoints){
                                    for(Segment segment:pt.getListSegment()){
                                        seg.add(segment);
                                    }
                                }
                                for(Segment segment:seg){
                                    if(segment.getDepart()==listePointsTemp.get(listePointsTemp.size()-1)){
                                        pointDepartValide=true;
                                    }
                                    if(segment.getArrivee()==tempPoint){
                                        pointArriveeValide=true;
                                    }
                                }
                                if(pointDepartValide==true&&pointArriveeValide==true){
                                    listePointsTemp.add(tempPoint);
                                    dessinateur.mettreSurbrillancePointItineraire(tempPoint,listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                                    updateCarte();
                                }
                                else{
                                    dessinateur.mettreSurbrillancePointItineraire(listePointsTemp.get(listePointsTemp.size()-1),listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                                    updateCarte();
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun segment ne permet de continuer l'itinéraire de cette manière", "Erreur",JOptionPane.ERROR_MESSAGE);
                                }
                                
                            }
                        }
                        //SI NOUS NE SOMMES PLUS SUR LE MEME CIRCUIT QUE LE PRÉCÉDENT
                        else{
                            resetVarItineraire();
                            dessinateur.resetAllSurbrillance();
                            updateCarte();
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, impossible de changer de circuit sur une intersection", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    //PLUS D'UN CIRCUIT
                    else{
                        String ok = "";
                        //ON VÉRIFIE SI LE DERNIER CIRCUIT PARCOURU PASSE PAR CE POINT
                        for(Circuit circ : listeCircIntersection){
                            if(circ==listeCircuitsTemp.get(listeCircuitsTemp.size()-1)){
                                ok=circ.getNomCircuit();
                            }
                        }
                        //SI NON
                        if(ok.isEmpty()){
                            resetVarItineraire();
                            dessinateur.resetAllSurbrillance();
                            updateCarte();
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, impossible de changer de circuit sur une intersection", "Erreur",JOptionPane.ERROR_MESSAGE); 
                        }
                        //SI OUI
                        else{
                            if(application.getCircuit(ok) instanceof AllerSimple && application.getCircuit(ok).getCircdepuisSource().get(0)!=application.getCircuit(ok).getCircdepuisSource().get(application.getCircuit(ok).getCircdepuisSource().size()-1)&&tempPoint==application.getCircuit(ok).getSource()){
                                resetVarItineraire();
                                dessinateur.resetAllSurbrillance();
                                updateCarte();
                                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Un problème est survenu, le circuit courant se termine sur cet intersection, impossible de changer de circuit à cet emplacement", "Erreur",JOptionPane.ERROR_MESSAGE); 
                            }
                            else{
                                listePointsTemp.add(tempPoint);
                                dessinateur.mettreSurbrillancePointItineraire(tempPoint,listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                                updateCarte();
                            }
                        }
                    }
                }
                //SI ON CLIC SUR NON - CONTINU L'AJOUT DE POINT
                else if(confirmation == JOptionPane.NO_OPTION && tempPoint instanceof Arret){
                    ArrayList<Circuit> circAValider = application.getCircDunPoint(tempPoint);
                    for(int i=0;i<application.getCircDunPoint(tempPoint).size();i++){
                        if(application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().get(0)!=application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().get(application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().size()-1) && 
                                application.getCircDunPoint(tempPoint).get(i) instanceof AllerSimple && application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().get(application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().size()-1)==tempPoint){
                            circAValider.remove(i);
                        }
                    }
                    //LE CIRCUIT QUI Y PASSE EST LE MEME QUE LE DERNIER POINT - NE L'AJOUTE PAS À LA LISTE
                    if(circAValider.size()>1 && tempPoint instanceof Arret){
                        choixCircuitAjoutIti();
                    }
                    //LE POINT A UN SEUL CIRCUIT
                    else{
                        if(tempPoint instanceof Arret && circAValider != null){
                            if(circAValider.size()==1 && circAValider.get(0) != listeCircuitsTemp.get(listeCircuitsTemp.size()-1)){
                                listeCircuitsTemp.add(circAValider.get(0));
                                listePivotsTemp.add(tempPoint);
                            }
                        }
                        listePointsTemp.add(tempPoint);
                        dessinateur.mettreSurbrillancePointItineraire(tempPoint,listeCircuitsTemp.get(listeCircuitsTemp.size()-1));
                        updateCarte();
                    }
                }
                //CANCEL
                else{
                    resetVarItineraire();
                }
            }//FIN ELSE - CLIQUE SUR NON LORS CONFIRMATION FIN CIRCUIT
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Permet de choisir un circuit qui sera ajouter à la liste de circuits directement
     */
    private void choixCircuitAjoutIti(){
        try{
            JLabel choixCircuitLabel = new JLabel("Veuillez choisir le circuit à emprunter : ");
            JComboBox listeCircuits = new JComboBox();
            ArrayList<Circuit> circAValider = application.getCircDunPoint(tempPoint);
            listeCircuits.addActionListener ((ActionEvent ev) -> {
            try{
                if(application.getCircuit(listeCircuits.getSelectedItem().toString().trim().toLowerCase())==null){}
                else{
                    for(int i=0;i<application.getCircDunPoint(tempPoint).size();i++){
                        if(application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().get(0)!=application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().get(application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().size()-1) && 
                                application.getCircDunPoint(tempPoint).get(i) instanceof AllerSimple && application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().get(application.getCircDunPoint(tempPoint).get(i).getCircdepuisSource().size()-1)==tempPoint){
                            circAValider.remove(i);
                        }
                    }
                    if(tempPoint==application.getCircuit(listeCircuits.getSelectedItem().toString().trim()).getCircdepuisSource().get(application.getCircuit(listeCircuits.getSelectedItem().toString().trim()).getCircdepuisSource().size()-1)&&application.getCircuit(listeCircuits.getSelectedItem().toString().trim())instanceof AllerSimple){
                        dessinateur.resetAllSurbrillance();
                        updateCarte();
                    }
                    else{
                        dessinateur.mettreSurbrillancePointItineraire(tempPoint,application.getCircuit(listeCircuits.getSelectedItem().toString().trim().toLowerCase()));
                        updateCarte();
                    }
                }
            }catch(uiExcept ex){
                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : "+ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            });
            for(Circuit circ:circAValider){
                listeCircuits.addItem(circ.getNomCircuit().toLowerCase().trim());
            }
            Object[] fields = {
                choixCircuitLabel,
                listeCircuits
            };
            int confirmationCircuit = JOptionPane.showOptionDialog(null, fields,"Choix du circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(confirmationCircuit == JOptionPane.OK_OPTION){
                if(application.getCircuit(listeCircuits.getSelectedItem().toString().trim())!=listeCircuitsTemp.get(listeCircuitsTemp.size()-1)){
                    ajouterCircuitAListe(application.getCircuit(listeCircuits.getSelectedItem().toString().trim().toLowerCase()));
                    listePivotsTemp.add(tempPoint);
                }
                else{
                    if(application.getCircDunPoint(tempPoint).get(listeCircuitsTemp.size()-1).getCircdepuisSource().get(application.getCircDunPoint(tempPoint).get(listeCircuitsTemp.size()-1).getCircdepuisSource().size()-1)==tempPoint && listeCircuitsTemp.get(listeCircuitsTemp.size()-1) instanceof AllerSimple){
                        listePointsTemp.add(tempPoint);
                        JLabel nomItineraireLabel = new JLabel("Nom de l'itinéraire : ");
                    JTextField nomItineraire = new JTextField("");
                    SpinnerModel model0 = new SpinnerNumberModel(0.0,0.0,99999999.0,0.1);
                    SpinnerModel model1 = new SpinnerNumberModel(1.0,1.0,99999999.0,0.1);
                    SpinnerModel model2 = new SpinnerNumberModel(1.0,1.0,99999999.0,0.1);
                    SpinnerModel model3 = new SpinnerNumberModel(1.0,1.0,99999999.0,0.1);
                    JLabel tempApparition = new JLabel("Temps apparition premier passager [minutes] : ");
                    JSpinner tempApparitionText = new JSpinner(model0);
                    JLabel minItineraire = new JLabel("Temps minimal entre apparition passagers [minutes] : ");
                    JSpinner minItineraireText = new JSpinner(model1);
                    JLabel modeItineraire = new JLabel("Temps modal entre apparition passagers [minutes] : ");
                    JSpinner modeItineraireText = new JSpinner(model2);
                    JLabel maxItineraire = new JLabel("Temps maximal entre apparition passagers [minutes] : ");
                    JSpinner maxItineraireText = new JSpinner(model3);
                    JFormattedTextField tfMaxPassIti = ((JSpinner.DefaultEditor) maxItineraireText.getEditor()).getTextField();
                    tfMaxPassIti.setEditable(false);
                    JFormattedTextField tfMinPassIti = ((JSpinner.DefaultEditor) minItineraireText.getEditor()).getTextField();
                    tfMinPassIti.setEditable(false);
                    JFormattedTextField tfModePassIti = ((JSpinner.DefaultEditor) modeItineraireText.getEditor()).getTextField();
                    tfModePassIti.setEditable(false);
                    Object[] fields2 = {
                    nomItineraireLabel,
                    nomItineraire,
                    tempApparition,
                    tempApparitionText,
                    minItineraire,
                    minItineraireText,
                    modeItineraire,
                    modeItineraireText,
                    maxItineraire,
                    maxItineraireText
                    };
                    int confirmationNomIti = JOptionPane.showOptionDialog(null, fields2,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    while(confirmationNomIti==JOptionPane.OK_OPTION){
                        ArrayList<Itineraire> listeDesIti = application.getListIti();
                        if(nomItineraire.getText().trim().isEmpty()){
                            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le nom de l'itinéraire ne peut être vide", "Erreur",JOptionPane.ERROR_MESSAGE);
                            confirmationNomIti = JOptionPane.showOptionDialog(null, fields2,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                        }
                        else{
                            if(confirmationNomIti==JOptionPane.OK_OPTION){
                                boolean existeDeja=false;
                                for(Itineraire iti : listeDesIti){
                                    if(iti.getNomIti().equals(nomItineraire.getText().trim().toLowerCase())){
                                        existeDeja=true;
                                    }
                                }
                                if(existeDeja==true){
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Ce nom de d'itinéraire existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                                    confirmationNomIti = JOptionPane.showOptionDialog(null, fields2,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                else if(Double.parseDouble(minItineraireText.getValue().toString())>Double.parseDouble(modeItineraireText.getValue().toString())||
                                    Double.parseDouble(modeItineraireText.getValue().toString())>Double.parseDouble(maxItineraireText.getValue().toString())||
                                    Double.parseDouble(minItineraireText.getValue().toString())>Double.parseDouble(maxItineraireText.getValue().toString())){
                                    JOptionPane.showMessageDialog(new JFrame(), "Erreur : Données min/mode/max non valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                                    confirmationNomIti = JOptionPane.showOptionDialog(null, fields,"Spécifications de l'itinéraire", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                                }
                                else{
                                    if(tempPoint!=listePointsTemp.get(listePointsTemp.size()-1)){
                                        listePointsTemp.add(tempPoint);
                                    }
                                    application.CreerItineraire(listePointsTemp, listeCircuitsTemp, listePivotsTemp,nomItineraire.getText().trim().toLowerCase(),
                                        (Double)tempApparitionText.getValue(),
                                        (Double)minItineraireText.getValue(),
                                        (Double)modeItineraireText.getValue(),
                                        (Double)maxItineraireText.getValue());
                                    JOptionPane.showMessageDialog(new JFrame(), "Itinéraire créé avec succès", "Succès",JOptionPane.INFORMATION_MESSAGE);
                                    remplirListeItineraire(nomItineraire.getText().trim().toLowerCase());
                                    ajoutObjectListeUndo(application.getIti(nomItineraire.getText().trim().toLowerCase()));
                                    resetVarItineraire();
                                    break;
                                }
                            }
                            else{
                                resetVarItineraire();
                                break;
                            }
                        
                        }
                    }
                    resetVarItineraire();   
                        
                        
                    }
                }
                listePointsTemp.add(tempPoint);
            }
            else{
                resetVarItineraire();
                dessinateur.resetAllSurbrillance();
                updateCarte(); 
            }
            }catch(uiExcept ex){
                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : "+ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
            }
    }
    
    /**
     * Permet de modifier le segment sélectionné dans la liste déroulante de segment
     */
    private void modifierSegment(){
        if(jComboBoxListeSegments.getSelectedIndex()==0){
            //TODO - Erreur
        }
        else{
        int indexSelect = jComboBoxListeSegments.getSelectedIndex();
        JLabel minSegmentTitre = new JLabel("Nouveau minimum [minutes] : ");
        JLabel modeSegmentTitre = new JLabel("Nouveau mode [minutes] : ");
        JLabel maxSegmentTitre = new JLabel("Nouveau maximum [minutes] : ");
        SpinnerModel model1 = new SpinnerNumberModel(listeTempSegment.get(indexSelect-1).getTempsMin(),1.0,99999999.0,0.1);
        SpinnerModel model2 = new SpinnerNumberModel(listeTempSegment.get(indexSelect-1).getTempsMode(),1.0,99999999.0,0.1);
        SpinnerModel model3 = new SpinnerNumberModel(listeTempSegment.get(indexSelect-1).getTempsMax(),1.0,99999999.0,0.1);
        JSpinner minSegment = new JSpinner(model1);
        JSpinner modeSegment = new JSpinner(model2);
        JSpinner maxSegment = new JSpinner(model3);
        JFormattedTextField tfMinSegment = ((JSpinner.DefaultEditor) minSegment.getEditor()).getTextField();
        tfMinSegment.setEditable(false);
        JFormattedTextField tfModeSegment = ((JSpinner.DefaultEditor) modeSegment.getEditor()).getTextField();
        tfModeSegment.setEditable(false);
        JFormattedTextField tfMaxSegment = ((JSpinner.DefaultEditor) maxSegment.getEditor()).getTextField();
        tfMaxSegment.setEditable(false);
        Object[] boutons = {
            minSegmentTitre,
            minSegment,
            modeSegmentTitre,
            modeSegment,
            maxSegmentTitre,
            maxSegment
        };
        int confirmation = JOptionPane.showOptionDialog(null, boutons,"Modiciation de segment", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
        while(confirmation==JOptionPane.OK_OPTION){
            if((Double)minSegment.getValue()>(Double)modeSegment.getValue()||(Double)modeSegment.getValue()>(Double)maxSegment.getValue()||(Double)minSegment.getValue()>(Double)maxSegment.getValue()){
                JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez saisir des données valide (Min<=Mode<=Max)", "Erreur",JOptionPane.ERROR_MESSAGE);
                confirmation = JOptionPane.showOptionDialog(null, boutons,"Modiciation de segment", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            }
            else{
                try{
                    application.modifSeg(listeTempSegment.get(indexSelect-1).getDepart(), listeTempSegment.get(indexSelect-1).getArrivee(),
                        Double.parseDouble(minSegment.getValue().toString()), 
                        Double.parseDouble(modeSegment.getValue().toString()),
                        Double.parseDouble(maxSegment.getValue().toString()));
                    break;
                }catch(uiExcept ex){
                   JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }   
        }
        }
    }
    
    /**
     * Permet de modifier le circuit sélectionné dans la liste
     */
    private void modifierCircuit(){
        if(jComboBoxListeCircuits.getItemCount()<=1 || jComboBoxListeCircuits.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Aucun circuit à modifier", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
        else{
            String nomCourantCircuit = jComboBoxListeCircuits.getSelectedItem().toString();
            Circuit circCourant=null;
            try{
                circCourant = application.getCircuit(nomCourantCircuit);
            }catch(uiExcept ex){
                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            JLabel nomCircuitTitre = new JLabel("Nom du circuit");
            JLabel maxBus = new JLabel("Nouveau nombre total de bus simultané(s) sur circuit [0 si infini] : ");
            JLabel minCircuitTitre = new JLabel("Nouveau temps minimal entre apparition véhicules [minutes]: ");
            JLabel modeCircuitTitre = new JLabel("Nouveau temps modal entre apparition véhicules [minutes]: ");
            JLabel maxCircuitTitre = new JLabel("Nouveau temps maximal entre apparition véhicules [minutes]: ");
            JTextField nomCircuit = new JTextField(nomCourantCircuit);
            SpinnerModel model0;
            if(circCourant.getCapaciteMaxCircuit()==-1){
                model0 = new SpinnerNumberModel(0,0,100,1);
            }
            else{
                model0 = new SpinnerNumberModel(circCourant.getCapaciteMaxCircuit(),0,100,1);
            }
            SpinnerModel model1 = new SpinnerNumberModel(circCourant.getMin(),1.0,99999999.0,0.1);
            SpinnerModel model2 = new SpinnerNumberModel(circCourant.getMode(),1.0,99999999.0,0.1);
            SpinnerModel model3 = new SpinnerNumberModel(circCourant.getMax(),1.0,99999999.0,0.1);
            JSpinner maxBusSpinner = new JSpinner(model0);
            JSpinner minCircuit = new JSpinner(model1);
            JSpinner modeCircuit = new JSpinner(model2);
            JSpinner maxCircuit = new JSpinner(model3);
            JFormattedTextField tfMinCircuit = ((JSpinner.DefaultEditor) minCircuit.getEditor()).getTextField();
            tfMinCircuit.setEditable(false);
            JFormattedTextField tfModeCircuit = ((JSpinner.DefaultEditor) modeCircuit.getEditor()).getTextField();
            tfModeCircuit.setEditable(false);
            JFormattedTextField tfMaxCircuit = ((JSpinner.DefaultEditor) maxCircuit.getEditor()).getTextField();
            tfMaxCircuit.setEditable(false);
            Object[] boutons = {
                nomCircuitTitre,
                nomCircuit,
                maxBus,
                maxBusSpinner,
                minCircuitTitre,
                minCircuit,
                modeCircuitTitre,
                modeCircuit,
                maxCircuitTitre,
                maxCircuit
            };
            int confirmation = JOptionPane.showOptionDialog(null, boutons,"Modiciation de circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(confirmation==JOptionPane.YES_OPTION){
                while(confirmation==JOptionPane.OK_OPTION){
                    try{
                    ArrayList<Circuit> temp = new ArrayList();
                    temp.addAll(application.getListCircuit());
                    if(nomCircuit.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Le nom du circuit ne peut être vide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        confirmation = JOptionPane.showOptionDialog(null, boutons,"Modiciation de circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    }
                    else if((temp.contains(application.getCircuit(nomCircuit.getText().trim())))&&!nomCircuit.getText().trim().equals(nomCourantCircuit)){
                           JOptionPane.showMessageDialog(new JFrame(), "Erreur : Ce nom de circuit existe déjà", "Erreur",JOptionPane.ERROR_MESSAGE);
                           confirmation = JOptionPane.showOptionDialog(null, boutons,"Modiciation de circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                        
                    }
                    else if(Double.parseDouble(minCircuit.getValue().toString())>Double.parseDouble(modeCircuit.getValue().toString())||
                            Double.parseDouble(modeCircuit.getValue().toString())>Double.parseDouble(maxCircuit.getValue().toString())||
                            Double.parseDouble(minCircuit.getValue().toString())>Double.parseDouble(maxCircuit.getValue().toString())){
                        JOptionPane.showMessageDialog(new JFrame(), "Erreur : Données min/mode/max non valide", "Erreur",JOptionPane.ERROR_MESSAGE);
                        confirmation = JOptionPane.showOptionDialog(null, boutons,"Modiciation de circuit", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
                    }
                    else{
                        //On modifie le circuit
                        application.modifParamCircuit(circCourant,
                                Double.parseDouble(minCircuit.getValue().toString()),
                                Double.parseDouble(modeCircuit.getValue().toString()), 
                                Double.parseDouble(maxCircuit.getValue().toString()),
                                Integer.parseInt(maxBusSpinner.getValue().toString()));
                        application.modifNomCircuit(nomCircuit.getText().trim(),circCourant);
                        dessinateur.updateApplication(application);
                        dessinateur.resetSurbrillanceReseauEntier();
                        dessinateur.repaint();
                        remplirListeCircuits();
                        break;
                    }
                    }catch(uiExcept ex){
                        JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
    
    /**
     * Permet de remplir la liste de circuit du UI en fonction de celle créée dans la variable application
     */
    private void remplirListeCircuits(){
        try{
            if(application.getListCircuit().isEmpty()){
                jComboBoxListeCircuits.removeAllItems();
                jComboBoxListeCircuits.addItem("Liste des Circuits");
            }
            else{
                jComboBoxListeCircuits.removeAllItems();
                jComboBoxListeCircuits.addItem("Liste des Circuits");
                for(Circuit circ : application.getListCircuit()){
                    jComboBoxListeCircuits.addItem(circ.getNomCircuit());
                }
            }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Permet de remplir la liste de circuits et d'en selectionner un passé en paramètre
     * @param nomCircuitSelect Nom du circuit à sélectionner 
     */
    private void remplirListeCircuits(String nomCircuitSelect){
        try{
            if(application.getListCircuit().isEmpty()){
                jComboBoxListeCircuits.removeAllItems();
                jComboBoxListeCircuits.addItem("Liste des Circuits");
            }
            else{
                jComboBoxListeCircuits.removeAllItems();
                jComboBoxListeCircuits.addItem("Liste des Circuits");
                for(Circuit circ : application.getListCircuit()){
                    jComboBoxListeCircuits.addItem(circ.getNomCircuit());
                }
                for(int i=0;i<jComboBoxListeCircuits.getItemCount();i++){
                    if(jComboBoxListeCircuits.getItemAt(i).toString().trim().equals(nomCircuitSelect.trim())){
                        jComboBoxListeCircuits.setSelectedIndex(i);
                    }
                }
            }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Permet de remplir la liste d'itinéraire
     */
    private void remplirListeItineraire(){
        jComboBoxListeItineraire.removeAllItems();
        jComboBoxListeItineraire.addItem("Liste des itinéraires");
        try{
            for(Itineraire iti : application.getListIti()){
                jComboBoxListeItineraire.addItem(iti.getNomIti().trim());
            }
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }   
    }
    
    /**
     * Permet de remplir la liste d'itineraires et de selectionner celui passé en paramètre
     * @param nomItineraireSelect Nomde l'itinéraire à selectionner
     */
    private void remplirListeItineraire(String nomItineraireSelect){
        remplirListeItineraire();
        for(int i=0;i<jComboBoxListeItineraire.getItemCount();i++){
            if(jComboBoxListeItineraire.getItemAt(i).equals(nomItineraireSelect.trim().toLowerCase())&&i!=0){
                jComboBoxListeItineraire.setSelectedIndex(i);
            }
        }
    }
    
    /**
     * Ajoute un circuit passé en parametre a la liste de circuits.  Met aussi à jour la liste de points du circuit
     * @param circ Circuit à ajouter
     */
    private void ajouterCircuitAListe(Circuit circ){
        listeCircuitsTemp.add(circ);
    }
    
    /**
     * Permet de faire les appels nécessaire pour mettre les points d'arrivées potentiels d'un point en rouge
     */
    private void mettreSurbrillancePoint(){
        try{
            dessinateur.mettreSurbrillancePoint(application.verifClicPoint(lastXLocationClic*(1/dessinateur.getZoomFactor()), jPanel1.getSize().height-((-(lastYLocationClic-jPanel1.getSize().height))*(1/dessinateur.getZoomFactor()))));
            dessinateur.updateApplication(application);
            dessinateur.repaint();
        }catch(uiExcept ex){
            JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Rempli les champs lors de la sélection d'un point
     */
    private void remplirChampsSelectionPoint(){
        //VERIFIE QUEL TYPE DE POINT IL S'AGIT POUR REMPLIR LES CHAMPS
        jComboBoxTypePoint.addItem("Arrêt");
        jComboBoxTypePoint.addItem("Intersection");
        jComboBoxTypePoint.setEnabled(true);
        if(tempPoint instanceof Arret){
            jComboBoxTypePoint.setSelectedIndex(0);
        }
        if(tempPoint instanceof Intersection){
            jComboBoxTypePoint.setSelectedIndex(1);
        }
        jTextFieldNom.setText(tempPoint.getNom());
        jTextFieldLatitude.setText(Double.toString(tempPoint.getCoord().getLat()));
        jTextFieldLongitude.setText(Double.toString(tempPoint.getCoord().getLon()));
        jComboBoxListeSegments.removeAllItems();
        //ON AFFICHE LES SEGMENTS ASSOCIÉS À CE POINT DANS LA LISTE
        int it=0;
        jComboBoxListeSegments.addItem("Liste des Segments : ");
        for(Segment seg : tempPoint.getListSegment()){
            if(!seg.getDepart().getNom().isEmpty()&&!seg.getArrivee().getNom().isEmpty()){
                jComboBoxListeSegments.addItem(seg.getDepart().getNom() + "->" + seg.getArrivee().getNom());
            }
            else{
                jComboBoxListeSegments.addItem("Segment " + it);
            }
            it++;
        }
    }
    
    /**
     * Permet de faire les appels nécessaire afin de mettre les points d'arrivées potentiels d'un point en rouge
     * @param pt Le point d'origine
     */
    private void mettreSurbrillancePoint(Point pt){
        dessinateur.mettreSurbrillancePoint(pt);
        dessinateur.updateApplication(application);
        dessinateur.repaint();
    }

    /**
     * Permet de faire les appels nécessaire afin de mettre les point possible lors de la création d'un réseau
     * @param pt Point source
     */
    private void mettreSurbrillancePointReseauDessinateur(Point pt){
        dessinateur.updateApplication(application);
        dessinateur.mettreSurbrillancePointReseau(pt);
        dessinateur.repaint();
    }
    
    /**
     * Méthode pour effectuer l'action d'annuler
     */
    private void undo(){
        try{  
            if(undoObjects.get(undoObjects.size()-1) instanceof Arret){
                ajoutObjectListeRedo(undoObjects.get(undoObjects.size()-1));
                application.supprPoint((Arret)undoObjects.get(undoObjects.size()-1));
                undoObjects.remove(undoObjects.size()-1);
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else if(undoObjects.get(undoObjects.size()-1) instanceof Intersection){
                ajoutObjectListeRedo(undoObjects.get(undoObjects.size()-1));
                application.supprPoint((Intersection)undoObjects.get(undoObjects.size()-1));
                undoObjects.remove(undoObjects.size()-1);
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else if(undoObjects.get(undoObjects.size()-1) instanceof Segment){
                application.supprSeg((Segment)undoObjects.get(undoObjects.size()-1));
                ajoutObjectListeRedo(undoObjects.get(undoObjects.size()-1));
                undoObjects.remove(undoObjects.size()-1);
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else if(undoObjects.get(undoObjects.size()-1) instanceof Circuit){
                application.supprCircuit((Circuit)undoObjects.get(undoObjects.size()-1));
                ajoutObjectListeRedo(undoObjects.get(undoObjects.size()-1));
                undoObjects.remove(undoObjects.size()-1);
                remplirListeCircuits();
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else if(undoObjects.get(undoObjects.size()-1) instanceof Itineraire){
                application.supprItineraire((Itineraire)undoObjects.get(undoObjects.size()-1));
                ajoutObjectListeRedo(undoObjects.get(undoObjects.size()-1));
                undoObjects.remove(undoObjects.size()-1);
                remplirListeItineraire();
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            
        }catch(Exception ex) {
            System.out.println(ex.getCause());
        }
    }
    
    /**
     * Méthode pour effectuer l'action répéter
     */
    private void redo(){
        try{
            if(redoObjects.get(redoObjects.size()-1) instanceof Point){
                application.ajouterPoint((Point)redoObjects.get(redoObjects.size()-1));    
                ajoutObjectListeUndo(redoObjects.get(redoObjects.size()-1));
                redoObjects.remove(redoObjects.size()-1);
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }    
            else if(redoObjects.get(redoObjects.size()-1) instanceof Segment){
                application.ajouterSegment((Segment)redoObjects.get(redoObjects.size()-1));
                ajoutObjectListeUndo(redoObjects.get(redoObjects.size()-1));
                redoObjects.remove(redoObjects.size()-1);
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else if(redoObjects.get(redoObjects.size()-1) instanceof Circuit){
                application.ajoutCircuit((Circuit)redoObjects.get(redoObjects.size()-1));
                ajoutObjectListeUndo(redoObjects.get(redoObjects.size()-1));
                redoObjects.remove(redoObjects.size()-1);
                remplirListeCircuits();
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else if(redoObjects.get(redoObjects.size()-1) instanceof Itineraire){
                application.ajoutItineraire((Itineraire)redoObjects.get(redoObjects.size()-1));
                ajoutObjectListeUndo(redoObjects.get(redoObjects.size()-1));
                redoObjects.remove(redoObjects.size()-1);
                remplirListeItineraire();
                dessinateur.resetAllSurbrillance();
                updateCarte();
            }
            else{
                
            }
        }catch(Exception ex) {
            System.out.println(ex.getCause());
        }
    }
    
    /**
     * Méthode permet d'ajouter un certain objet à la liste pour annulation
     * @param obj L'objet
     */
    private void ajoutObjectListeUndo(Object obj){
        if(dernierUndoRedo=="redo"){
            undoObjects.clear();
        }
        undoObjects.add(obj);
        dernierUndoRedo="undo";
    }
    
    /**
     * Méthode permet d'ajouter un certain objet à la liste pour répétition
     * @param obj L'objet
     */
    private void ajoutObjectListeRedo(Object obj){
        if(dernierUndoRedo=="undo"){
            redoObjects.clear();
        }
        redoObjects.add(obj);
        dernierUndoRedo="redo";
    }
    
    
    
    //--------------------------
    //MÉTHODES ÉLÉMENTS VISUELS (BOUTONS, TEXTFIELD, ETC.)
    //--------------------------
    
    /**
     * Met à jour la carte avec l'état actuel du graphe
     */
    private void updateCarte(){
        dessinateur.updateApplication(application);
        dessinateur.repaint();
    }
    
    /**
     * Active les champs du menu Graphe
     */
    private void activeChampsGraphe(){
        jToggleButtonPoint.setEnabled(true);
        jToggleButtonSegment.setEnabled(true);
        jComboBoxTypePoint.setEnabled(true);
        jButtonModifierPointType.setEnabled(true);
        jTextFieldNom.setEnabled(true);
        jButtonModifierPoint.setEnabled(true);
        jTextFieldLatitude.setEnabled(true);
        jTextFieldLongitude.setEnabled(true);
        jToggleButtonDeplacerPoint.setEnabled(true);
        jButtonSupprimerPoint.setEnabled(true);
        jComboBoxListeSegments.setEnabled(true);
        jButtonModifierSegment.setEnabled(true);
        jButtonSupprimerSegment.setEnabled(true);
    }
    
    /**
     * Active la modification des champs du menu Graphe
     */
    private void activeModifChampsGraphe(){
        jTextFieldNom.setEditable(true);
    }
    
    /**
     * Active les champs du menu Réseau
     */
    private void activeChampsReseau(){
        jComboBoxListeCircuits.setEnabled(true);
        jButtonModifierCircuit.setEnabled(true);
        jButtonSupprimerCircuit.setEnabled(true);
        jButtonRefreshCircuits.setEnabled(true);
        jComboBoxListeItineraire.setEnabled(true);
        jButtonRefreshListeIti.setEnabled(true);
        jToggleButtonCircuit.setEnabled(true);
        jToggleButtonItineraire.setEnabled(true);
    }
    
    /**
     * Active les champs du menu simulation
     */
    private void activeChampsSim(){
        jComboBoxListeSims.setEnabled(true);
        jTextFieldNomSim.setEnabled(true);
        jSpinnerDebutSim.setEnabled(true);
        jSpinnerFinSim.setEnabled(true);
        jButtonCreerSim.setEnabled(true);
    }
    
    /**
     * Active la modification des champs du menu simulation
     */
    private void activeModifChampsSim(){
        jTextFieldNomSim.setEditable(true);
    }
    
    /**
     * Désactive les champs du menu graphe
     */
    private void desactiveChampsGraphe(){
        jToggleButtonPoint.setEnabled(false);
        jToggleButtonSegment.setEnabled(false);
        jComboBoxTypePoint.setEnabled(false);
        jButtonModifierPointType.setEnabled(false);
        jTextFieldNom.setEnabled(false);
        jButtonModifierPoint.setEnabled(false);
        jTextFieldLatitude.setEnabled(false);
        jTextFieldLongitude.setEnabled(false);
        jToggleButtonDeplacerPoint.setEnabled(false);
        jButtonSupprimerPoint.setEnabled(false);
        jComboBoxListeSegments.setEnabled(false);
        jButtonModifierSegment.setEnabled(false);
        jButtonSupprimerSegment.setEnabled(false);
    }
    
    /**
     * Désactive la modification des champs du menu graphe
     */
    private void desactiveModifChampsGraphe(){
        jTextFieldNom.setEditable(false);
    }
    
    /**
     * Désactive les champs du menu reseau
     */
    private void desactiveChampsReseau(){
        jComboBoxListeCircuits.setEnabled(false);
        jButtonModifierCircuit.setEnabled(false);
        jButtonSupprimerCircuit.setEnabled(false);
        jButtonRefreshCircuits.setEnabled(false);
        jComboBoxListeItineraire.setEnabled(false);
        jButtonRefreshListeIti.setEnabled(false);
        jToggleButtonCircuit.setEnabled(false);
        jToggleButtonItineraire.setEnabled(false);
    }
    
    /**
     * Désactive les champs du menu Simulation
     */
    private void desactiveChampsSim(){
        jComboBoxListeSims.setEnabled(false);
        jTextFieldNomSim.setEnabled(false);
        jSpinnerDebutSim.setEnabled(false);
        jSpinnerFinSim.setEnabled(false);
        jButtonCreerSim.setEnabled(false);
    }
    
    /**
     * Désactive la modifications des champs du menu Simulation
     */
    private void desactiveModifChampsSim(){
        jTextFieldNomSim.setEditable(false);
    }
    
    /**
     * Active tous les boutons du menu d'outils graphe/réseau/simulation
     */
    private void activerTousBoutons(){
        jToggleButtonPoint.setEnabled(true);
        jToggleButtonSegment.setEnabled(true);
        jToggleButtonCircuit.setEnabled(true);
        jToggleButtonItineraire.setEnabled(true);
        jToggleButtonEditerSource.setEnabled(true);
        //TODO - À Compléter
    }
    
    /**
     * Désactive les boutons du menu d'outils
     */
    private void desactiverTousBoutons(){
        jToggleButtonPoint.setEnabled(false);
        jToggleButtonSegment.setEnabled(false);
        jToggleButtonCircuit.setEnabled(false);
        jToggleButtonItineraire.setEnabled(false);
        jToggleButtonEditerSource.setEnabled(false);
        //TODO - À Compléter
    }
    
    /**
     * Méthode qui désélectionne tous les boutons 
     */
    private void deselectionBoutons(){
        jToggleButtonPoint.setSelected(false);
        jToggleButtonSegment.setSelected(false);
        jToggleButtonCircuit.setSelected(false);
        jToggleButtonItineraire.setSelected(false);
        jToggleButtonDemarrerSim.setSelected(false);
        jToggleButtonEditerSource.setSelected(false);
    }
        
    /**
     * Charge le mode Standard
     * Vide tous les champs, liste, et remet les boutons a l'état initial, Affiche le graphe a nouveau tout en noir
     */
    //TODO (Olivier) A Completer
    private void chargerModeStandard(){
        activeChampsGraphe();
        activeModifChampsGraphe();
        activeChampsReseau();
        activeChampsSim();
        activeModifChampsSim();
        deselectionBoutons();
        viderChampsGraphe();
        activerTousBoutons();
        jToggleButtonDemarrerSim.setText("Démarrer");
        jComboBoxMode.setSelectedIndex(0);
        dateCourante = new Date();
        iterSimulation=0;
    }
    
    /**
     * Charge le mode Édition
     */
    //TODO (Olivier) A Completer
    private void chargerModeEdition(){
        activeChampsGraphe();
        activeModifChampsGraphe();
        activeChampsReseau();
        activeChampsSim();
        activeModifChampsSim();
        deselectionBoutons();
        viderChampsGraphe();
        activerTousBoutons();
        jToggleButtonDemarrerSim.setText("Démarrer");
        jComboBoxMode.setSelectedIndex(1);
        dessinateur.resetAllSurbrillance();
        updateCarte();
    }
    
    /**
     * Charge le mode simulation
     */
    //TODO (Olivier) A Completer
    private void chargerModeSimulation(){
        desactiverTousBoutons();
        desactiveChampsReseau();
        desactiveChampsGraphe();
        desactiveModifChampsGraphe();
        deselectionBoutons();
        jComboBoxMode.setSelectedIndex(2);
    }
    
    /**
     * Vide les champs du menu graphe (nom points, type etc.)
     */
    private void viderChampsGraphe(){
        jTextFieldNom.setText("");
        jComboBoxTypePoint.removeAllItems();
        jComboBoxTypePoint.setEditable(false);
        jComboBoxTypePoint.setEnabled(true);
        jTextFieldLatitude.setText("");
        jTextFieldLongitude.setText("");
        jComboBoxListeSegments.removeAllItems();
    }   
    
    /**
     * Charge la vue par défaut de l'application (lors ouverture)
     */
    private void chargeVueDefault(){
        jToggleButtonMinimizeDetailsSelection.setSelected(false);
        jPanelDetailsSelection.setVisible(true);
        jToggleButtonMinimizeDetailsSegments.setSelected(false);
        jPanelDetailsSegments.setVisible(true);
        jToggleButtonMinimizeReseauCirc.setSelected(false);
        jPanelListeCircuits.setVisible(true);
        jToggleButtonMinimizeReseauIti.setSelected(false);
        jPanelReseauListeIti.setVisible(true);
    }
    
    /**
     * Permet de charger une simulation
     */
    private void chargerSimulation(){
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(new FileNameExtensionFilter("Archive SimulatHEURE (*.sthr)", "sthr"));
        int returnVal = chooser.showOpenDialog(null);
        File fichier = chooser.getSelectedFile();
        if(returnVal==JFileChooser.APPROVE_OPTION && fichier!=null){
            try{
                application.loadSim(fichier.getAbsolutePath());
            }catch(uiExcept ex){
                JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            chargerModeStandard();
            jTabbedPaneGauche.setSelectedIndex(2);
            jComboBoxListeSims.removeAllItems();
            jComboBoxListeSims.addItem("Liste des simulations");
            nomProjet=application.getListSim().get(0).getNomSim();
            jLabelNomProjet.setText("Projet : " + nomProjet);
            for(Simulation sim : application.getListSim()){
                jComboBoxListeSims.addItem(sim.getNomSim().trim());
            }
            jComboBoxListeSims.setSelectedIndex(jComboBoxListeSims.getItemCount()-1);
            updateCarte();
            remplirListeCircuits();
            remplirListeItineraire();
        }
    }
    
    /**
     * Permet de sauvegarder la simulation sélectionnée dans la liste de simulations
     */
    private void sauvegarderSim(){
        if(jComboBoxListeSims.getItemCount()>1&&jComboBoxListeSims.getSelectedIndex()!=0){
            int confirmation = JOptionPane.showOptionDialog(null, "Sauvegarder la simulation " + jComboBoxListeSims.getSelectedItem().toString().trim() + "?","Sauvegarder", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
            if(confirmation==JOptionPane.YES_OPTION){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(nomProjet+".sthr"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archive SimulatHEURE (*.sthr)", "sthr"));
                int returnVal = fileChooser.showSaveDialog(null);
                if(returnVal==JFileChooser.APPROVE_OPTION){
                    File fichier = fileChooser.getSelectedFile();
                    try{
                        if(fichier.toString().substring(fichier.toString().lastIndexOf('.') + 1).equals("sthr")){
                            application.saveSim(fichier.toString(), application.getSimParNom(jComboBoxListeSims.getSelectedItem().toString().trim()));
                        }
                        else{
                            application.saveSim(fichier.toString()+".sthr", application.getSimParNom(jComboBoxListeSims.getSelectedItem().toString().trim()));
                        }
                        
                    }catch(uiExcept ex){
                        JOptionPane.showMessageDialog(new JFrame(), "ERREUR : " + ex.getMessage(), "Erreur",JOptionPane.ERROR_MESSAGE);
                    }
                }   
                else{
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Erreur : Veuillez créer une simulation via l'onglet 'Simulation -> Création de simulation' afin de la sauvegarder", "Erreur",JOptionPane.ERROR_MESSAGE);
        }
    }
      
    
    
    //------------------------
    //SETUP
    //------------------------
    private void loadDefaultVarValues(){
        nomProjet = "nouveauProjet1";
        iterSegment=0;//Itérateur compte le nombre de clic lors création segment
        iterReseau=0;//Itérateur compte nombre de clic lors création circuit
        iterSimulation=0;//Iterateur permettant d'identifier si une simulation a deja ete demarrée sans être terminée
        iterItineraire=0;////Itérateur compte nombre de clic lors création circuit
        tempPoint = null;//Variable temporaire pour stockage d'instance de points
        tempSegment1 = null;//Variable temporarire pour stockage d'instance de segments
        listeTempSegment = new ArrayList();//Variable temporaire pour stockage liste de segments lors clic
        nomCircuitModif=null;//Permet de se rappeler du nom du circuit dont le point source est différent du point de depart
        modifierSourceCircuit=false;//Boolean indiquant si nous sommes en mode édition point source circuit
        listePointsTemp = new ArrayList();//ArrayList stockage des points d'un circuit
        listeCircuitsTemp = new ArrayList();//ArrayList stockage des circuits lors création itinéraire 
        listePivotsTemp = new ArrayList();//ArrayList stockage des points pivots lors création itinéraire 
        boucle=false;//Valeur definissant circuit boucle ou non
        minReseau = 0;//Valeur du min lors création reseau avec point source different point depart
        modeReseau = 0;//Valeur du mode lors création reseau avec point source different point depart
        maxReseau = 0;//Valeur du max lors création reseau avec point source different point depart
        maxBusReseau = 0;//Valeur du nombre max de bus sur circuit lors création reseau avec point source different point depart
        lastXLocationClic=0;//Dernier emplacement du clic de souris (X)
        lastYLocationClic=0;//Dernier emplacement du clic souris (Y)
        date1 = new Date();//Date de début de la simulation
        date2 = new Date();//Date de fin de la simulation
        dateCourante = new Date();//Date courante (de la simulation)
        timerAjust = 1000;//Variable ajustement vitesse timer
        iterFinSim=false;//Permet de savoir si la simulation a été arrêtée
        resetSim = false;//Permet d'identifier si nous devons reinitialiser les simulations
        deplacerPoint = false;//Défini si nous sommes en mode déplacement de points
        undoObjects = new ArrayList();//Liste contenant les items pouvait être annulé
        redoObjects = new ArrayList();//Liste contenant les items pouvant etre recréé suite à l'annulation
        dernierUndoRedo = "";//Permet d'identifier si nous avons effectué un undo suivi d'un redo 
    }
    
    /**
     * Charge tous les parametres par defaut lors du chargement de l'application
     */
    private void loadDefaultSetup(){
        loadDefaultVarValues();
        chargeVueDefault();
        jLabelNomProjet.setText("Projet : " + nomProjet);
        //Création des deux controleurs de souris
        ControleurSourisMouvements controleurDeplacement = new ControleurSourisMouvements();
        ControleurSourisClics controleur = new ControleurSourisClics();
        ControleurRouletteSouris controleurRoulette = new ControleurRouletteSouris();
        carte.setSize(jPanel1.getSize());
        carte.setResizable(false);
        setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI internalFrame= carte.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame).setNorthPane(null);
        jPanel1.add(carte);
        carte.addMouseMotionListener(controleurDeplacement);
        carte.addMouseListener(controleur);
        carte.addMouseWheelListener(controleurRoulette);
        carte.setVisible(true);
        dessinateur= new Dessinateur();
        carte.add(dessinateur);   
        dessinateur.setBackgroundType(jComboBoxTypeCarte.getSelectedIndex());
    }
    
    /**
     * Redefini les variables utilisées lors de la création d'un itinéraire à leur état d'origine
     */
    private void resetVarItineraire(){
        listePointsTemp.clear();
        listeCircuitsTemp.clear();
        listePivotsTemp.clear();
        iterItineraire=0;
    }
    
    /**
     * Permet de remettre à 0 les variables utilisées lors de la création d'un circuit
     */
    private void resetVarReseau(){
        listePointsTemp.clear();
        minReseau = 0;
        modeReseau = 0;
        maxReseau = 0;
        maxBusReseau = 0;
        iterReseau=0;
        boucle=false;
    }
        
    /**
     * Main 
     * @param args 
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfacePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new InterfacePrincipale().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonArreterSimulation;
    private javax.swing.JButton jButtonCreerSim;
    private javax.swing.JButton jButtonMinimiseParamSim;
    private javax.swing.JButton jButtonModifierCircuit;
    private javax.swing.JButton jButtonModifierPoint;
    private javax.swing.JButton jButtonModifierPointType;
    private javax.swing.JButton jButtonModifierSegment;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonOpen;
    private javax.swing.JButton jButtonRapport;
    private javax.swing.JButton jButtonRedo;
    private javax.swing.JButton jButtonRefreshCircuits;
    private javax.swing.JButton jButtonRefreshListeIti;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSupprimerCircuit;
    private javax.swing.JButton jButtonSupprimerItineraire;
    private javax.swing.JButton jButtonSupprimerPoint;
    private javax.swing.JButton jButtonSupprimerSegment;
    private javax.swing.JButton jButtonUndo;
    private javax.swing.JButton jButtonZoomIn;
    private javax.swing.JButton jButtonZoomOut;
    private javax.swing.JComboBox jComboBoxListeCircuits;
    private javax.swing.JComboBox jComboBoxListeItineraire;
    private javax.swing.JComboBox jComboBoxListeSegments;
    private javax.swing.JComboBox jComboBoxListeSims;
    private javax.swing.JComboBox jComboBoxMode;
    private javax.swing.JComboBox jComboBoxTypeCarte;
    private javax.swing.JComboBox jComboBoxTypePoint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelCircuitsTitre;
    private javax.swing.JLabel jLabelCircuitsTitre1;
    private javax.swing.JLabel jLabelDetailsSegments;
    private javax.swing.JLabel jLabelDetailsSelection;
    private javax.swing.JLabel jLabelHeureDebSim;
    private javax.swing.JLabel jLabelHeureFinSim;
    private javax.swing.JLabel jLabelLatitude;
    private javax.swing.JLabel jLabelLongitude;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelNomProjet;
    private javax.swing.JLabel jLabelNomSImul;
    private javax.swing.JLabel jLabelOutilsGraphe;
    private javax.swing.JLabel jLabelOutilsReseau;
    private javax.swing.JLabel jLabelOutilsSimulation;
    private javax.swing.JLabel jLabelSimulationParametresTitre;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelValueSpeed;
    private javax.swing.JLabel jLabelX;
    private javax.swing.JLabel jLabelXCoordonnees;
    private javax.swing.JLabel jLabelY;
    private javax.swing.JLabel jLabelYCoordonnees;
    private javax.swing.JLabel jLabelZoom;
    private javax.swing.JMenu jMenuAide;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdition;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItemAPropos;
    private javax.swing.JMenuItem jMenuItemAnnuler;
    private javax.swing.JMenuItem jMenuItemEnregistrer;
    private javax.swing.JMenuItem jMenuItemInformation;
    private javax.swing.JMenuItem jMenuItemPreferences;
    private javax.swing.JMenuItem jMenuItemQuitter;
    private javax.swing.JMenuItem jMenuItemRepeter;
    private javax.swing.JMenu jMenuOutils;
    private javax.swing.JMenuItem jMenuOuvrir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBackMain;
    private javax.swing.JPanel jPanelBoutonsGraphe;
    private javax.swing.JPanel jPanelBoutonsGrapheTitre;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelCircuitsTitre;
    private javax.swing.JPanel jPanelDetailsSegments;
    private javax.swing.JPanel jPanelDetailsSegmentsTitre;
    private javax.swing.JPanel jPanelDetailsSelection;
    private javax.swing.JPanel jPanelDetailsSelectionTitre;
    private javax.swing.JPanel jPanelGraphe;
    private javax.swing.JPanel jPanelGrapheContainer;
    private javax.swing.JPanel jPanelLeft;
    private javax.swing.JPanel jPanelListeCircuits;
    private javax.swing.JPanel jPanelOutilsReseau;
    private javax.swing.JPanel jPanelPrincipalResizable;
    private javax.swing.JPanel jPanelReseau;
    private javax.swing.JPanel jPanelReseauContainer;
    private javax.swing.JPanel jPanelReseauListeIti;
    private javax.swing.JPanel jPanelReseauOutilsTitre;
    private javax.swing.JPanel jPanelSimulation;
    private javax.swing.JPanel jPanelSimulationContainer;
    private javax.swing.JPanel jPanelSimulationOutils;
    private javax.swing.JPanel jPanelSimulationOutilsTitre;
    private javax.swing.JPanel jPanelSimulationParametres;
    private javax.swing.JPanel jPanelSimulationParametresTitre;
    private javax.swing.JPanel jPanelTitreReseauItineraire;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneCenter;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparatorDetailsSelection;
    private javax.swing.JSeparator jSeparatorReseauOutilsTitre;
    private javax.swing.JSeparator jSeparatorSimulationOutilsTitre;
    private javax.swing.JSeparator jSeparatorSimulationParametres;
    private javax.swing.JSeparator jSeparatorTopGraphe;
    private javax.swing.JSlider jSliderVitesseSimulation;
    private javax.swing.JSpinner jSpinnerDebutSim;
    private javax.swing.JSpinner jSpinnerFinSim;
    private javax.swing.JSpinner jSpinnerQteSim;
    private javax.swing.JTabbedPane jTabbedPaneGauche;
    private javax.swing.JTextField jTextFieldHeureCourante;
    private javax.swing.JTextField jTextFieldHeureDebut;
    private javax.swing.JTextField jTextFieldHeureFin;
    private javax.swing.JTextField jTextFieldLatitude;
    private javax.swing.JTextField jTextFieldLongitude;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldNomSim;
    private javax.swing.JToggleButton jToggleButtonCircuit;
    private javax.swing.JToggleButton jToggleButtonDemarrerSim;
    private javax.swing.JToggleButton jToggleButtonDeplacerPoint;
    private javax.swing.JToggleButton jToggleButtonEditerSource;
    private javax.swing.JToggleButton jToggleButtonItineraire;
    private javax.swing.JToggleButton jToggleButtonMinimizeBoutonsGraphe;
    private javax.swing.JToggleButton jToggleButtonMinimizeBoutonsReseau;
    private javax.swing.JToggleButton jToggleButtonMinimizeBoutonsSimulation;
    private javax.swing.JToggleButton jToggleButtonMinimizeDetailsSegments;
    private javax.swing.JToggleButton jToggleButtonMinimizeDetailsSelection;
    private javax.swing.JToggleButton jToggleButtonMinimizeReseauCirc;
    private javax.swing.JToggleButton jToggleButtonMinimizeReseauIti;
    private javax.swing.JToggleButton jToggleButtonPoint;
    private javax.swing.JToggleButton jToggleButtonSegment;
    // End of variables declaration//GEN-END:variables
}
