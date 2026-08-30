package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.SottNotExistException;
import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Sottomissione;
import com.unicam.hackhub.Model.Team;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class GestoreSottomissione {

    private static Map<Integer, Sottomissione> sottomissioneRepository = new HashMap<>();
    private static Map<Team, Integer> teamToSottomissione = new HashMap<>();
    private static Integer tot = 0;

    public Sottomissione aggiornaSottomissione(Hackathon hackathon, Team team, MultipartFile file, String fileName){

        if (teamToSottomissione.containsKey(team)){
            Sottomissione sottomissione = sottomissioneRepository.get( teamToSottomissione.get(team) );
            delete(sottomissione.getFilePath());
            upload(file,fileName);
            sottomissione.setFilePath(fileName);
            return sottomissione;
        }
        else {
            upload(file,fileName);
            Sottomissione sottomissione = new Sottomissione(tot,team,hackathon,fileName);
            sottomissioneRepository.put(sottomissione.getId(),sottomissione);
            teamToSottomissione.put(team,sottomissione.getId());
            tot++;
            return sottomissione;
        }
    }

    public void InviaGiudice(Integer sottId){
        if (sottomissioneRepository.containsKey(sottId)){
            Sottomissione sottomissione = sottomissioneRepository.get( sottId );
            sottomissione.inviaGiudice();

        }else {
            throw new SottNotExistException();
        }
    }

    public Sottomissione getSottomissione(Hackathon hackathon, String nomeTeam){

        return sottomissioneRepository
                .values()
                .stream()
                .filter(x->x.getHackathon().equals(hackathon))
                .filter(y->y.getTeam().getNome().equals(nomeTeam))
                .filter(Sottomissione::isInviatoGiudice)
                .findFirst()
                .orElseThrow(SottNotExistException::new);

    }

    private void upload(MultipartFile file, String fileName){
        File uploadedFile = new File("src/main/resources/"+fileName);

        try {
            uploadedFile.createNewFile();
            FileOutputStream fileStream = new FileOutputStream(uploadedFile);
            fileStream.write(file.getBytes());
            fileStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void delete(String fileName){
        Path fileToDeletePath = Paths.get("src/main/resources/"+fileName);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
