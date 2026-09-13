package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.SottNotExistException;
import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Sottomissione;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Repository.SottomissioneRepository;
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
import java.util.Optional;

@Service
public class GestoreSottomissione {

   /* private static Map<Integer, Sottomissione> sottomissioneRepository = new HashMap<>();
    private static Map<Team, Integer> teamToSottomissione = new HashMap<>();
    private static Integer tot = 0;
    */

    private SottomissioneRepository sottomissioneRepository;

    public GestoreSottomissione(SottomissioneRepository sottomissioneRepository) {
        this.sottomissioneRepository = sottomissioneRepository;
    }

    public Sottomissione aggiornaSottomissione(Hackathon hackathon, Team team, MultipartFile file, String fileName){

       // if (teamToSottomissione.containsKey(team)){
       //     Sottomissione sottomissione = sottomissioneRepository.get( teamToSottomissione.get(team) );
        Optional<Sottomissione> optSottomissione = sottomissioneRepository.findByTeam(team);
        if (optSottomissione.isPresent()) {

            Sottomissione sottomissione =  optSottomissione.get();
            delete(sottomissione.getFilePath());
            upload(file,fileName);
            sottomissione.setFilePath(fileName);
            return sottomissione;
        }
        else {
            upload(file,fileName);
            Sottomissione sottomissione = new Sottomissione(team,hackathon,fileName);
            /*sottomissioneRepository.put(sottomissione.getId(),sottomissione);
            teamToSottomissione.put(team,sottomissione.getId());
            tot++;*/
            sottomissioneRepository.save(sottomissione);
            return sottomissione;
        }
    }

    public void InviaGiudice(Long sottId){
        /*if (sottomissioneRepository.containsKey(sottId)){
            Sottomissione sottomissione = sottomissioneRepository.get( sottId );
            sottomissione.inviaGiudice();

        }else {
            throw new SottNotExistException();
        }*/
        sottomissioneRepository
                .findById(sottId)
                .orElseThrow(SottNotExistException::new)
                .inviaGiudice();
    }

    public Sottomissione getSottomissione(Hackathon hackathon, String nomeTeam){

       /* return sottomissioneRepository
                .values()
                .stream()
                .filter(x->x.getHackathon().equals(hackathon))
                .filter(y->y.getTeam().getNome().equals(nomeTeam))
                .findFirst()
                .orElseThrow(SottNotExistException::new);*/

        return sottomissioneRepository
                .findByHackathonAndTeam_NomeTeam(hackathon,nomeTeam)
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
