package sk.stuba.fei.oop.transformers;

import sk.stuba.fei.oop.generated.Document;
import sk.stuba.fei.oop.gui.Drawable;
import sk.stuba.fei.oop.petrinet.PetriNet;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @projct: netModeller
 * @author: Pavel Sadlon
 * @date: 2019
 */
public class FileHandler {

    private List<Drawable> drawables = new LinkedList<>();
    private PetriNet petriNet = new PetriNet();

    public List<Drawable> selectFile() {

        PetriNetTransformer pt = new PetriNetTransformer();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");

        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setDialogTitle("Select Petri net file");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(filter);
        chooser.showOpenDialog(null);

        try {
            InputStream resource = ClassLoader.getSystemResourceAsStream(chooser.getSelectedFile().getName());
            JAXBContext context = JAXBContext.newInstance(Document.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Document document = (Document) unmarshaller.unmarshal(resource);

            petriNet = pt.transform(document);
            DrawableTransformer dt = new DrawableTransformer(petriNet);
            drawables = dt.transform(document);
        } catch (JAXBException | NullPointerException e) {
            System.out.println(e);
        }
        return drawables;
    }

    public void saveFile(Document document) {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");

        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        chooser.setDialogTitle("Save Petri net");
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(filter);
        chooser.showSaveDialog(null);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Document.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(document, new File("output.xml"));
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }
}
