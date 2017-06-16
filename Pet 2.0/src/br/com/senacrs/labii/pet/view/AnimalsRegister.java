package br.com.senacrs.labii.pet.view;

import br.com.senacrs.labii.pet.db.DBConnection;
import br.com.senacrs.labii.pet.model.Owner;
import java.text.ParseException;

import br.com.senacrs.labii.pet.model.Animal;
import br.com.senacrs.labii.pet.util.Data;
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

                                String dono = data.readString("Código ou Nome do Dono: ");

                                if (verifyOwner(dono) == true) {

                                    for (int i = 0; i < Main.owners.size(); i++) {

                                        if (dono.equals(Main.owners.get(i).getName()) || dono.equals(Integer.toString(Main.owners.get(i).getCod()))) {

                                            animal.setOwner(Main.owners.get(i));
                                            donoOk = true;

                                        }

                                    }

                                }

                            }

                        }

                    }

                }

            }

        }

        animal.setCod(Main.countRegAnimals + 1);

        Animal.addToArrayList(animal);

        data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");

        ClientRegister.menuAnimals();

    }

    public void updateAnimal() throws ParseException, SQLException {

        if (Main.animals.isEmpty()) {

            data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");

            ClientRegister.menuAnimals();

        } else {

            data.message("\n\n - Alterar dados do Animal -\n\n");

            while (Animal.searchToUpdate(data.readString("Digite o Código ou nome do animal: ")) == false) {

                data.message("\n\n .:: ERRO!!! Animal não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");

            ClientRegister.menuAnimals();

        }

    }

    public void removeAnimal() throws ParseException, SQLException {

        if (Main.animals.isEmpty()) {

            data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");

            ClientRegister.menuAnimals();

        } else {

            data.message("\n\n - Remover Animal -\n\n");

            while (Animal.searchToRemove(data.readString("Digite o Código ou nome do animal: ")) == false) {

                data.message("\n\n .:: ERRO!!! Animal não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");

            ClientRegister.menuAnimals();

        }

    }

    public void listAnimals() throws ParseException, SQLException {

        if (Main.animals.isEmpty()) {

            data.message("\n\nERRO!!! Não há animais cadastrados!\n\n");

            ClientRegister.menuAnimals();

        } else {

            data.message("\n\n - Clientes - Animais - \n\n"
                    + "Cód. | Tipo | Raça | Nome | Dono\n");

            Animal.listAll();

        }

    }

    public String verifyType(int type) {

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

    public boolean verifyOwner(String search) {

        boolean found = false;
        
        found = dbc.verifyOwner(search);
        
        if(owner)
        
        for (int i = 0; i < Main.owners.size(); i++) {

            if (owner.equals(Integer.toString(Main.owners.get(i).getCod())) || owner.equals(Main.owners.get(i).getName())) {

                found = true;

                i = Main.owners.size();

            } else {

                found = false;

            }

        }

        if (found == false) {

            data.message("\nCliente não encontrado!!! Tente novamente.\n");

        }

        return found;

    }

    public boolean raceIsOk(String race) {

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

    public boolean nameIsOk(String name) {

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
