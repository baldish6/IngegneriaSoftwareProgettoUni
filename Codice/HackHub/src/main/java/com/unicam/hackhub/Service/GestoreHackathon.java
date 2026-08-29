package com.unicam.hackhub.Service;

import com.unicam.hackhub.Error.HackathonExistException;
import com.unicam.hackhub.Error.HackathonNotExistException;
import com.unicam.hackhub.Model.Giudice;
import com.unicam.hackhub.Model.Hackathon;
import com.unicam.hackhub.Model.Mentore;
import com.unicam.hackhub.Model.Team;
import com.unicam.hackhub.Util.HackathonInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GestoreHackathon {

    private static Map<Integer, Hackathon> hackathonRepository = new HashMap<>();

    public Hackathon addHackathon(HackathonInfo hackathon, Giudice giudice, Mentore mentore) {
        Hackathon hackathon1 = new Hackathon(
                hackathon.id(),hackathon.nome(),hackathon.regolamento(),
                hackathon.dataFineIscrizione(),hackathon.dataInizio(),hackathon.dataFine(),
                hackathon.luogo(),hackathon.premio(),hackathon.maxTeam(),giudice,mentore
        );
        if (!hackathonRepository.containsKey(hackathon.id())) {
            hackathonRepository.put(hackathon.id(), hackathon1);
            return hackathonRepository.get(hackathon.id());
        }
        else {
            throw new HackathonExistException();
        }
    }

    public Collection<Hackathon> getListHackathon(){
        return hackathonRepository.values();
    }

    public Boolean addMentore(Mentore mentore, Integer hackathonId){

        if (hackathonRepository.containsKey(hackathonId)){
            return hackathonRepository.get(hackathonId)
                    .addMentore(mentore);
        }
        else{
            throw new HackathonNotExistException();
        }
    }

    public Collection<Hackathon> getListHackathonLiberi(Team team){
        Integer size = team.getSize();
        return hackathonRepository
                .values()
                .stream()
                .filter(x->x.getMaxTeam()>=size).collect(Collectors.toList());
    }

    public Hackathon iscriviHackathon(Integer hackathonId,Team team){
        if (hackathonRepository.containsKey(hackathonId)){
            Hackathon hackathon = hackathonRepository.get(hackathonId);
            hackathon.iscriviHackathon(team);
            return hackathon;
        }else {
            throw new HackathonNotExistException();
        }

    }


}
