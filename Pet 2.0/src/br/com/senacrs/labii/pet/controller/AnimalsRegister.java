package br.com.senacrs.labii.pet.controller;

import br.com.senacrs.labii.pet.db.DBConnection;
import java.text.ParseException;

import br.com.senacrs.labii.pet.model.Animal;
import br.com.senacrs.labii.pet.model.Owner;
import br.com.senacrs.labii.pet.util.Data;
import br.com.senacrs.labii.pet.view.ClientRegisterView;
import java.sql.SQLException;

public class AnimalsRegister {

    static Data data = new Data();
    static DBConnection dbc = new DBConnection();

    public void registerAnimal() throws ParseException, SQLException {

        data.message("\n\n - Cadastrar animal -\n\n");

        Animal animal = new Animal();

        boolean tipoOk = false, raceOk = false, nameOk = false, donoOk = false;

        while (tipoOk == false) {

            data.message("- Tipo -\n\n1. Cachorro\n2. Gato\n3. Outros\n");

            int op = data.readInt("Escolha um opção: ");

            if (verifyType(op) != null) {

                animal.setType(verifyType(op));
                tipoOk = true;

                while (raceOk == false) {

                    String race = data.readString("Raça: ");

                    if (raceIsOk(race) == true) {

                        animal.setRace(race);
                        raceOk = true;

                        while (nameOk == false) {

                            String name = data.readString("Nome: ");

                            if (nameIsOk(name) == true) {

                                animal.setName(name);
                                nameOk = true;

                            }

                            while (donoOk == false) {

                                int dono = data.readInt("Código do Dono: ");

                                if (verifyOwner(dono) == true) {

                                    Owner owner = null;
                                    
                                    owner.setCod(dono);
                                    
                                    animal.setCodOwner(owner.getCod());
                                    donoOk = true;

                                }

                            }

                        }

                    }

                }

            }

        }

        dbc.insertAnimals(dbc.connect(), animal);

        data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");

        ClientRegisterView.menuAnimals();

    }

    public void updateAnimal() throws ParseException, SQLException {

        if (dbc.verifyHasAnimals(dbc.connect()) == false) {

            data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");

            ClientRegisterView.menuAnimals();

        } else {

            data.message("\n\n - Alterar dados do Animal -\n\n");

            while (searchToUpdate(data.readString("Digite o Código ou nome do animal: ")) == false) {

                data.message("\n\n .:: ERRO!!! Animal não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");

            ClientRegisterView.menuAnimals();

        }

    }

    public static boolean searchToUpdate(String search) throws SQLException {

        boolean found = false;

        Animal animal = new Animal();

        animal = dbc.searchAnimals(dbc.connect(), search);

        if (animal != null) {

            data.message("\n\n - Dados do Animal - \n\n"
                    + "1. Tipo: " + animal.getType()
                    + "\n2. Raça: " + animal.getRace()
                    + "\n3. Nome: " + animal.getName());

            String op = data.readString("\n\nQue informação deseja alterar? ");

            switch (op) {

                case "1":

                    boolean typeOk = false;

                    while (typeOk == false) {

                        data.message("- Type -\n\n1. Cachorro\n2. Gato\n3. Outros\n");

                        int opType = data.readInt("Escolha um opção: ");

                        if (verifyType(opType) != null) {

                            animal.setType(AnimalsRegister.verifyType(opType));
                            typeOk = true;

                        }

                    }

                    break;

                case "2":

                    boolean raceOk = false;

                    while (raceOk == false) {

                        String newRace = data.readString("Digite a Raça: ");

                        if (raceIsOk(newRace) == true) {

                            animal.setRace(newRace);
                            raceOk = true;
                        }

                    }

                    break;

                case "3":

                    boolean nameOk = false;

                    while (nameOk == false) {

                        String newName = data.readString("Digite o Nome: ");

                        if (nameIsOk(newName) == true) {

                            animal.setName(newName);
                            nameOk = true;
                            
                        }

                    }

                    break;

            }
            
            dbc.updateAnimals(dbc.connect(), animal);

        }

        return found;

    }
    
    public void removeAnimal() throws ParseException, SQLException {

        if (dbc.verifyHasAnimals(dbc.connect()) == true) {

            data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");

            ClientRegisterView.menuAnimals();

        } else {

            data.message("\n\n - Remover Animal -\n\n");

            while (searchToRemove(data.readString("Digite o Código ou nome do animal: ")) == false) {

                data.message("\n\n .:: ERRO!!! Animal não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");

            ClientRegisterView.menuAnimals();

        }

    }

    public static boolean searchToRemove(String search) throws SQLException {

        boolean found = false;

        Animal animal = new Animal();
        
        animal = dbc.searchAnimals(dbc.connect(), search);
        
        if(animal != null){
        
            dbc.removeAnimal(dbc.connect(), animal);
            
            found = true;
            
        } else {
        
            found = false;
            
        }
        
        return found;

    }
    
    public void listAnimals() throws ParseException, SQLException {

        if (dbc.verifyHasAnimals(dbc.connect()) == true) {

            data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");

            ClientRegisterView.menuAnimals();

        } else {

            data.message("\n\n - Clientes - Animais - \n\n"
                    + "Cód. | Tipo | Raça | Nome | Dono\n");

            dbc.listAllAnimals(dbc.connect());
            
            ClientRegisterView.menuAnimals();

        }

    }

    public static String verifyType(int type) {

        String op = Integer.toString(type);

        String typeAnimal = null;

        switch (op) {

            case "1":
                typeAnimal = "Cachorro";
                break;

            case "2":
                typeAnimal = "Gato";
                break;

            case "3":
                typeAnimal = "Outros";
                break;

            default:
                data.message("\n\nERRO!!!\nDigite uma opção válida!!\n");
                break;

        }

        return typeAnimal;

    }

    public boolean verifyOwner(int search) throws SQLException {

        boolean found = false;
        
        found = dbc.verifyOwner(dbc.connect(), search);

        if (found == false) {

            data.message("\nCliente não encontrado!!! Tente novamente.\n");

        }

        return found;

    }

    public static boolean raceIsOk(String race) {

        boolean hasNumbers = false;

        int contChars = race.length();

        for (int i = 0; i < race.length(); i++) {

            contChars--;

            if (!Character.isLetter(race.charAt(contChars))) {

                hasNumbers = true;

            }

        }

        if (hasNumbers == true) {

            data.message("\n.:: ERRO!!! \nDigite a raça corretamente.\n\n");

            return false;

        } else {

            return true;

        }

    }

    public static boolean nameIsOk(String name) {

        boolean hasNumbers = false;

        int contChars = name.length();

        for (int i = 0; i < name.length(); i++) {

            contChars--;

            if (!Character.isLetter(name.charAt(contChars))) {

                hasNumbers = true;

            }

        }

        if (hasNumbers == true) {

            data.message("\n.:: ERRO!!! \nDigite o nome corretamente.\n\n");

            return false;

        } else {

            return true;

        }

    }

}
