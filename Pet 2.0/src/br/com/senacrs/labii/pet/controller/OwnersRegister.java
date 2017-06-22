package br.com.senacrs.labii.pet.controller;

import br.com.senacrs.labii.pet.db.DBConnection;
import br.com.senacrs.labii.pet.view.ClientRegisterView;
import java.text.ParseException;

import br.com.senacrs.labii.pet.model.Owner;
import br.com.senacrs.labii.pet.util.Data;
import java.sql.SQLException;

public class OwnersRegister {

    static Data data = new Data();
    static DBConnection dbc = new DBConnection();

    public void registerOwner() throws ParseException, SQLException {

        data.message("\n\n - Cadastrar dono -\n\n");

        Owner owner = new Owner();

        boolean cpfOk = false, nameOk = false, emailOk = false;

        while (cpfOk == false) {

            String cpf = data.readString("CPF: ");

            if (cpfIsOk(cpf) == true) {

                owner.setCpf(cpf);
                cpfOk = true;

                while (nameOk == false) {

                    String name = data.readString("Nome: ");

                    if (nameIsOk(name) == true) {

                        owner.setName(name);
                        nameOk = true;

                        while (emailOk == false) {

                            String email = data.readString("E-mail: ");

                            if (emailIsOk(email) == true) {

                                owner.setEmail(email);
                                emailOk = true;
                            }

                        }

                    }

                }

            }

        }

        dbc.insertOwners(dbc.connect(), owner);

        data.message("\n\n .:: CADASTRADO COM SUCESSO!!!\n\n");

        ClientRegisterView.menuOwners();

    }

    public void updateOwner() throws ParseException, SQLException {

        if (dbc.verifyHasClients(dbc.connect()) == false) {

            data.message("\n\nERRO!!! Não há clientes cadastrados!\n\n");

            ClientRegisterView.menuOwners();

        } else {

            data.message("\n\n - Alterar dados do Cliente -\n\n");

            while (searchToUpdate(data.readString("Digite o Código ou CPF do cliente: ")) == false) {

                data.message("\n\n .:: ERRO!!! Cliente não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: ALTERADO COM SUCESSO!!!\n\n");

            ClientRegisterView.menuOwners();

        }

    }

    static boolean searchToUpdate(String search) throws SQLException {

        boolean found = false;
        String ownerCod;

        Owner owner = new Owner();

        owner = dbc.searchClients(dbc.connect(), search);

        if (owner != null) {

            data.message("\n\n - Dados do cliente - \n\n"
                    + "1. CPF: " + owner.getCpf()
                    + "\n2. Nome: " + owner.getName()
                    + "\n3. E-mail: " + owner.getEmail());

            String op = data.readString("\n\nQue informação deseja alterar? ");

            switch (op) {

                case "1":

                    boolean cpfOk = false;

                    while (cpfOk == false) {

                        String newCpf = data.readString("Digite o CPF: ");

                        if (OwnersRegister.cpfIsOk(newCpf) == true) {

                            owner.setCpf(newCpf);
                            cpfOk = true;

                        }

                    }

                    break;

                case "2":

                    boolean nameOk = false;

                    while (nameOk == false) {

                        String newName = data.readString("Digite o nome: ");

                        if (OwnersRegister.nameIsOk(newName) == true) {

                            owner.setName(newName);
                            nameOk = true;

                        }

                    }

                    break;

                case "3":

                    boolean emailOk = false;

                    while (emailOk == false) {

                        String newEmail = data.readString("Digite o e-mail: ");

                        if (OwnersRegister.emailIsOk(newEmail) == true) {

                            owner.setEmail(newEmail);
                            emailOk = true;

                        }

                    }

                    break;

            }

            dbc.updateOwners(dbc.connect(), owner);

            found = true;

        } else {

            found = false;

        }

        return found;

    }

    public static boolean searchToRemove(String search) throws SQLException {

        boolean found = false;

        Owner owner = new Owner();

        owner = dbc.searchClients(dbc.connect(), search);

        if (owner != null) {
        
            dbc.removeOwner(dbc.connect(), owner);
            
            found = true;

        } else {

            found = false;

        }
        
        return found;

    }

    public void removeOwner() throws ParseException, SQLException {

        if (dbc.verifyHasClients(dbc.connect()) == false) {

            data.message("\n\nERRO!!! Não há clientes cadastrados!\n\n");

            ClientRegisterView.menuOwners();

        } else {

            data.message("\n\n - Remover Cliente -\n\n");

            while (searchToRemove(data.readString("Digite o Código, CPF ou nome do cliente: ")) == false) {

                data.message("\n\n .:: ERRO!!! Cliente não encontrado!\n .:: Tente novamente.\n\n");

            }

            data.message("\n\n .:: REMOVIDO COM SUCESSO!!!\n\n");

            ClientRegisterView.menuOwners();

        }

    }

    public void listOwners() throws ParseException, SQLException {

        if (dbc.verifyHasClients(dbc.connect()) == false) {

            data.message("\n\nERRO!!! Não há clientes cadastrados!\n\n");

            ClientRegisterView.menuOwners();

        } else {

            data.message("\n\n - Clientes - DONOS - \n\n"
                    + "Cód. | CPF           | Nome | E-mail\n");
            
            dbc.listAllOwners(dbc.connect());

            ClientRegisterView.menuOwners();

        }

    }

    public static boolean cpfIsOk(String cpf) {

        boolean hasLetters = false;

        int contChars = cpf.length();

        for (int i = 0; i < cpf.length(); i++) {

            contChars--;

            if (Character.isLetter(cpf.charAt(contChars))) {

                hasLetters = true;

            }

        }

        if (cpf.length() < 14 || !cpf.contains(".") || !cpf.contains("-") || hasLetters == true || cpf.isEmpty()) {

            data.message("\n.:: ERRO!!! \nDigite o CPF corretamente. Formato (xxx.xxx.xxx-xx)\n\n");

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

        if (hasNumbers == true || name.isEmpty()) {

            data.message("\n.:: ERRO!!! \nDigite o nome corretamente.\n\n");

            return false;

        } else {

            return true;

        }

    }

    public static boolean emailIsOk(String email) {

        if (!email.contains("@") || !email.contains(".") || email.isEmpty()) {

            data.message("\n.:: ERRO!!! \nDigite o e-mail corretamente.\n\n");

            return false;

        } else {

            return true;

        }

    }

}
