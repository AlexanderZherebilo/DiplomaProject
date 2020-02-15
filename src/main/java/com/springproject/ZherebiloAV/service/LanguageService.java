package com.springproject.ZherebiloAV.service;

import com.springproject.ZherebiloAV.domain.Language;
import com.springproject.ZherebiloAV.repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageService {
    @Autowired
    LanguageRepo languageRepo;

    public List<Language> findAll() {
        List<Language> list = new ArrayList<>();
        Iterable<Language> it = languageRepo.findAll();
        for (Language e : it)
            list.add(e);
        return list;
    }
}
