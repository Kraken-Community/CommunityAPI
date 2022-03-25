package com.rshub.api.definitions;

import com.rshub.definitions.InventoryDefinition;
import com.rshub.definitions.VarbitDefinition;
import com.rshub.definitions.objects.ObjectDefinition;
import com.rshub.filesystem.Filesystem;
import com.rshub.filesystem.sqlite.SqliteFilesystem;

import java.nio.file.Paths;
import java.util.function.Consumer;

public class CacheHelper {
    private CacheHelper(){}
    private static final String CACHE_PATH = "C:\\ProgramData\\Jagex\\RuneScape";
    private static final Filesystem FS = new SqliteFilesystem(Paths.get(CACHE_PATH));
    private static final InventoryManager invManager = new InventoryManager(FS);
    private static final VarbitManager varbitManager = new VarbitManager(FS);
    private static final ObjectManager objectManager = new ObjectManager(FS);

    public static InventoryDefinition getInventory(int id) {
        return invManager.get(id);
    }

    public static VarbitDefinition getVarbit(int id) {
        return varbitManager.get(id);
    }

    public static ObjectDefinition getObject(int id) {
        return objectManager.get(id);
    }

    public static void decode(Consumer<Filesystem> consumer) {
        consumer.accept(FS);
    }
}
