package PackPhysique;

import PackPhysique.Component.Component;

public class Map extends Thread{

    private MoteurPhysique moteurPhysique;
    private Component[] components;
    private int positionInListComponent;

    private static int SIZE_LIST_COMPONENT = 30;

    public Map(MoteurPhysique moteurPhysique) {
        this.moteurPhysique = moteurPhysique;
        positionInListComponent = 0;
        components = new Component[SIZE_LIST_COMPONENT];
    }

    @Override
    public void run() {
        super.run();
    }


    public void addNewComponent(Component component){
        components[positionInListComponent] = component;
        component.run();
        positionInListComponent++;
    }

    public void deleteAComponent(Component component){
        for (int i=0; i<SIZE_LIST_COMPONENT;i++){
            if (components[i] != null){
                if (components[i].getId() == component.getId()){
                    components[i] = null;
                    return;
                }
            }
        }
        System.out.println("Fail to delete the component");
    }

    public Component[] getListComponent() {
        int i = 0;
        for (int x = 0; x < SIZE_LIST_COMPONENT; x++){
            if(components[x]!=null){
                i++;
            }
        }
        Component[] returnListComponent = new Component[i];
        i = 0;
        for (int x = 0; x < SIZE_LIST_COMPONENT; x++){
            if(components[x]!=null){
                returnListComponent[i] = components[x];
                i++;
            }
        }
        return returnListComponent;
    }
}
