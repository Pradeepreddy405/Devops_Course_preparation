```
             PROCESS MANAGEMENT
                    │
     ┌──────────────┼──────────────┐
     ↓              ↓              ↓
  SERVICE          PROCESS         PORT
  STATUS           STATUS          STATUS
     │              │              │
systemctl        ps/top           ss
     │              │              │
     └──────────────┼──────────────┘
                    ↓
              CPU / MEMORY
                    ↓
              PROCESS TREE
                    ↓
                  LOGS
                    ↓
          STOP / RESTART / KILL
```