package ru.clothingstore.service;

import org.springframework.data.domain.Page;
import ru.clothingstore.model.good.Good;

import java.util.List;
import java.util.Set;

public interface GoodService {

    public Good getGoodByTitle(String name);

    public Good getGoodByTitle(String name, boolean active);

    public Good getGoodById(int id);

    public Good getGoodById(int id, boolean active);

    public Set<Good> getAllGoods();

    public Page<Good> getAllGoods(boolean active, int offset, int limit, String sort);

    public Set<Good> getGoods(int count);

    public Set<Good> getGoods(int count, boolean active);

    public Set<Good> getLastAddedGoods(int count);

    public Set<Good> getLastAddedGoods(int count, boolean active);

    public Set<Good> getGoodsByCategory(int categoryId);

    public Page<Good> getGoodsByCategory(int categoryId, boolean active, int offset, int limit, String sort);

    public Set<Good> getGoodsByCategory(int count, int categoryId);

    public Set<Good> getGoodsByCategory(int count, int categoryId, boolean active);

    public List<Good> searchByTitle(String title);

    void addGood(Good good);

    void updateGood(Good good);

    void deleteGood(int id);
}
