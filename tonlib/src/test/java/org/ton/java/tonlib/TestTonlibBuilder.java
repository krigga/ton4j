package org.ton.java.tonlib;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.tonlib.types.VerbosityLevel;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(JUnit4.class)

public class TestTonlibBuilder {
    @Test
    public void testTonlibBuilderWindows() {
        if (SystemUtils.IS_OS_WINDOWS) {
            Tonlib tonlib = Tonlib.builder()
                    .pathToTonlibSharedLib("G:\\DOCKER\\mnt\\tonlibjson.dll")
                    .pathToGlobalConfig("G:\\DOCKER\\mnt\\testnet-global.config.json")
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderWindowsIntegratedLib() {
        if (SystemUtils.IS_OS_WINDOWS) {
            Tonlib tonlib = Tonlib.builder()
                    .pathToGlobalConfig("G:\\DOCKER\\mnt\\testnet-global.config.json")
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderWindowsIntegratedConfig() {
        if (SystemUtils.IS_OS_WINDOWS) {
            Tonlib tonlib = Tonlib.builder()
                    .pathToTonlibSharedLib("G:\\DOCKER\\mnt\\tonlibjson.dll")
                    .keystorePath("G:\\DOCKER\\mnt\\")
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderWindowsIgnoreCache() {
        if (SystemUtils.IS_OS_WINDOWS) {
            Tonlib tonlib = Tonlib.builder()
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .ignoreCache(false)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();

            tonlib = Tonlib.builder()
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .ignoreCache(true)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderWindowsConfigAsString() {
        if (SystemUtils.IS_OS_WINDOWS) {
            Tonlib tonlib = Tonlib.builder()
                    .globalConfigAsString(globalConfigAsString)
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderWindowsIntegratedLibAndConfig() {
        if (SystemUtils.IS_OS_WINDOWS) {
            Tonlib tonlib = Tonlib.builder().build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderUbuntu() {
        if (SystemUtils.IS_OS_LINUX) {
            Tonlib tonlib = Tonlib.builder()
                    .pathToTonlibSharedLib("/mnt/tonlibjson.so")
                    .pathToGlobalConfig("/mnt/testnet-global.config.json")
                    .verbosityLevel(VerbosityLevel.FATAL)
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderUbuntuIntegratedLib() {
        if (SystemUtils.IS_OS_LINUX) {
            Tonlib tonlib = Tonlib.builder()
                    .pathToGlobalConfig("/mnt/testnet-global.config.json")
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderUbuntuIntegratedConfig() {
        if (SystemUtils.IS_OS_LINUX) {
            Tonlib tonlib = Tonlib.builder()
                    .pathToTonlibSharedLib("/mnt/tonlibjson.so")
                    .build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    @Test
    public void testTonlibBuilderUbuntuIntegratedLibAndConfig() {
        if (SystemUtils.IS_OS_LINUX) {
            Tonlib tonlib = Tonlib.builder().build();
            assertThat(tonlib.getLast()).isNotNull();
        }
    }

    String globalConfigAsString = """
            {
              "@type": "config.global",
              "dht": {
                "@type": "dht.config.global",
                "a": 3,
                "k": 6,
                "static_nodes": {
                  "@type": "dht.nodes",
                  "nodes": [
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "C1uy64rfGxp10SPSqbsxWhbumy5SM0YbvljCudwpZeI="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1307380867,
                            "port": 15888
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "s+tnHMTzPYG8abau+1dUs8tBJ+CDt+jIPmGfaVd7nmfb1gt6lL10G2IwkNeWhkxjZcAHRc0azWFVxp+IjIOOBQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "bn8klhFZgE2sfIDfvVI6m6+oVNi1nBRlnHoxKtR9WBU="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1307380860,
                            "port": 15888
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "fQ5zAa6ot4pfFWzvuJOR8ijM5ELWndSDsRhFKstW1tqVSNfwAdOC7tDC8mc4vgTJ6fSYSWmhnXGK/+T5f6sDCw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "KlNrfVSyO6oISNi4Bx8J2klAN4RnKmEPQpfr1bghGSk="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1307380856,
                            "port": 15888
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "0/1trU+HDc+Co/q8gw5lPrSJH9YCOXxVh0caR2CGqXE5820DguuSmVnnLQ9S2+RmxHv0biYZuH8FiJv2wPwyDA=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "HU0bQDDmXnUENi2qQgGUQSopWz+s0dFA1l6NgB6HdQ0="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1185526389,
                            "port": 26907
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "RrZILQv72PtJ/oADGh+txXgo6qfUL9RFLU+YjMXsMZTAo2lCWYwNoeFOEZrS5MKfLmkL6O0MmOR/EEAFrr3mAw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "xHfihhu4rFeKUNjxH2aHCJIG1s9PTaypqjocrm82U24="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 1560268637,
                            "port": 29503
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "m+ah3Bn9AMaV13QaXrfT/c/z1fY6DzDMQkCEIgByXygnskwYwTfWxa8Z7DXy80UX2OYpSL8GwTJ4HpQsdcxMCA=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "4R0C/zU56k+x2HGMsLWjX2rP/SpoTPIHSSAmidGlsb8="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1952265919,
                            "port": 14395
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "0uwWyCFn2KjPnnlbSFYXLZdwIakaSgI9WyRo87J3iCGwb5TvJSztgA224A9kNAXeutOrXMIPYv1b8Zt8ImsrCg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "/YDNd+IwRUgL0mq21oC0L3RxrS8gTu0nciSPUrhqR78="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1402455171,
                            "port": 14432
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "6+oVk6HDtIFbwYi9khCc8B+fTFceBUo1PWZDVTkb4l84tscvr5QpzAkdK7sS5xGzxM7V7YYQ6gUQPrsP9xcLAw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "DA0H568bb+LoO2LGY80PgPee59jTPCqqSJJzt1SH+KE="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1402397332,
                            "port": 14583
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "cL79gDTrixhaM9AlkCdZWccCts7ieQYQBmPxb/R7d7zHw3bEHL8Le96CFJoB1KHu8C85iDpFK8qlrGl1Yt/ZDg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "fZnkoIAxrTd4xeBgVpZFRm5SvVvSx7eN3Vbe8c83YMk="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 1091897261,
                            "port": 15813
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "cmaMrV/9wuaHOOyXYjoxBnckJktJqrQZ2i+YaY3ehIyiL3LkW81OQ91vm8zzsx1kwwadGZNzgq4hI4PCB/U5Dw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "zDBLsKjns4bBqQokzY0wOzC2vwbOeiE1J7aOjfCp5mg="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1573440928,
                            "port": 12821
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "qORMhem9RyG7wnNYF822YL3EXwEoTO82h2TarFbjd0jikMIGizAdir1JyxSfyKkhHdFKGcLMeoPb2dfMIvQwAA=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "CU9ytJok8WBnpl29T740gfC/h69kgvQJp7FJMq/N60g="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 391653587,
                            "port": 15895
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "DKyGF2nPRxmerpIHxE5FN1Lod3zvJu728NP0iYc1hpNyPvl5epu+7amjimLy1VdzNqFzTJAoJ/gqPPMkXS/kDw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "d1lL6xZO8UWMzkWD++8Yr3hf6585X6qoOZZTeLtGl4o="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1537553981,
                            "port": 18513
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "sDxyCuvZmi9fJvQl4DEBKZOlt532xflnVD1dvC2ia2Na5MN8dT6x4HizEpS4pUqky8LzR/A/4BCqIQXhD45vDg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "SP2Bjle9u+GoZhlEhm3mIPiND3Yh+Nr7QsgV6bGPw/I="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1591529174,
                            "port": 11369
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "qbbeE53SOyUvOWD2hIZmTyZGY4k2XHxgldqBIakBma33oC5sWDD/+cApuFLMbi2Gnd6fQtQ3LaZtfQzJrCiYAw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "76SsMv8NrqzkCFEVumZEx2phYYSUZvSH3UlUTuPdZYk="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1906451518,
                            "port": 16937
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "YgrIN/KH3XVPyk09wgZbFaXLZ/BeGjvfO6ohxN4M8Tp/CsYN0G3tNLSjJGeuH22bIKMFRoEpp3v7Sz54Q/FTCg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "IHrCooudVGonmudcmTZYk+Pfdsxz2NGws33bHtXnOv8="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1882838653,
                            "port": 41623
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "pm3PQn0ZYQH7SkmK/8q4p1igFKxJSQLCmVcFtyaRxEV9ecjvB5PiW3o3wfrTRPk0v0mnFmH8UuYtLEztj+LcBw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "eDPKd9v6aHhutnHU3z2ykV4eUA3LdFI+oSBg6z8JyS0="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1481864647,
                            "port": 22261
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "InY5jkkQ6fuJtutkmlPLYEhqQ0F4DyEhqoPB4KMBIJc8hhZFzm4jNAxSfy3VVQbACdJ/pj76qbjaktG/m1ipDQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "znOAvy1ECxyzeKishi4PdSO2edaVx78wynVyNKLBmQ8="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1068377703,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "KLH17nNKmOk3carKwbsUcVBc4JZpdAUdUOMxe8FSyqnkOw/lolnltbylJcC+lvPpIV5ySI/Qx8UZdNRV/4HzCA=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "Qjhv9rmeqXm0a+nYYhCJG1AH7C2TM6DAmyIM3FgO0Eo="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1057912003,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "2Gw5eIsZR+SdbWCU139DCuBI8Rv8T9iUioxDkgV6/IjcCHf6hNz8WCyUsKd5l5P1NBs/kdaxUBIybINDpYXoCw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "2YsTRIu3aRYzZe8eoR8PK2N2ydHJyKllwKcLPk676d4="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1057911744,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "9/TJsaj0wELvRKXVIrBdyZWjgLKhfSvl7v0Oqq/9p9MsU/t9iRuGcpAzHqQF4bQAWrN8j9ARwMumRata7dH8Bg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "SHrXmMEEUBGa51TWZwHSA+2RF4Vyavw51jgtnAz1ypU="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -1057911148,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "R4ku8+tvjKSLIGe18zWHBHDv1maQHD5tGbAUOgbldGpBvfqH+/b76XkJjJzDsjnCO/bpxwUZfcI1sM1h6vFJCQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "G+Lr6UtSWUcyYHTUutwbxrIG9GGZan3h96j8qQPLIXQ="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -960017601,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "fWU9hSNLvmaSCQOBW9M4Lja5pIWcqOzU1g9vtSywdgtASj9oQpwAslvr2sjNh9E2Np1c26NW8Sc5gUKf8YY7BA=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "/tp8WsXfk/XpzOyaaxuOlvbOhDoH7/L81eWi0QMn0gg="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 84478511,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "2kA9P0LBI7H8gmmGsnZ2bQPZP3rZDFugrc5zQWlFrPIMLvwH7/J69HIGCVYgcaEsf0HMnIJeUMl5n4qFp0quBQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "fnYl5kAHcbhK65FbYxfwk83X1Sn6ZiuXWMD0F0Rh+v4="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 84478479,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "h+K+YttdhqE4LzihZTnKLFBiXyY79Rqqcx8dCbkDVXu3FD7ZrTBNV5b/bf7BQbuF0PXTc7YqH0jEmqz8aX6aBg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "HwOhm4Vh1YGqBNmUrDwJpeo8kXAPI7J3sSH38JaAyzQ="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -2018145068,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "Ianf1Wm9Y6HT9r32LFNUieKi86cSBbCckczHy+ZyBo15MpIsZxouUgfAyeW20sZm1hN5+Yx4lPwzL+Ovm6KaCw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "CXo+qxdYclubZqoqvVhoeYDdPV+VhlWcurf2OX0iPZs="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -2018145059,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "P72kraIX5pIxQBnh7It4kyK6MPuZ56ZFZKZxegtrxwx/Vpi1wQ4PsfxWf6N0HojbNMYsVZsvwHYTLxj5nhd6Dw=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "KiKtUV+kJWBd+M29zNvtRqdvUrtX4lfi5CyY+DRm+lk="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 1091931625,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "GjarYvxTVPik8m5yI9Eq/1lW/8CuReBdhUdFUb4wJJVVc/EvHf7j47mY5ECskHjeo9MYttgF/9KQaf8KNea1Dg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "o15mg8SB9CY2m971NvU+aCzAEnZFg3iAnIsqBMmqnj0="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 1091931590,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "6mJPM7RZMOL5uCMRCGINjxAG7L7LHt7o89caD7Kk75ahpwAhqJ3ri9zL1rzJZjmyOMLkPoGcckJsG8phCRbVDQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "VCu471G41Hj8onyyeJdq8t6AZu3SR7BoGuCLs8SppBk="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 1091931623,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "7cOhypsjGb4xczR20M6eg7ly8sdvzdodkKVXzvr00FsXHcguz6bP0zm/dwhiQgsJgSosYypCk/LvKQrMy+C3AQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "0uEnHB6Rg4sVjiepDgHoZ3CuKjCRjU3ul4IGmmZZoig="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": 1091931589,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "ju40qeS5mgbJDMLUxL7qSquSdqgo3Uib4Z/Va/bpIWJJA0W3VQStJMBbV/pQySi6MoM794Du3o8Gl1bjdpwDAg=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "MJr8xja0xpu9DoisFXBrkNHNx1XozR7HHw9fJdSyEdo="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -2018147130,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "XcR5JaWcf4QMdI8urLSc1zwv5+9nCuItSE1EDa0dSwYF15R/BtJoKU5YHA4/T8SiO18aVPQk2SL1pbhevuMrAQ=="
                    },
                    {
                      "@type": "dht.node",
                      "id": {
                        "@type": "pub.ed25519",
                        "key": "Fhldu4zlnb20/TUj9TXElZkiEmbndIiE/DXrbGKu+0c="
                      },
                      "addr_list": {
                        "@type": "adnl.addressList",
                        "addrs": [
                          {
                            "@type": "adnl.address.udp",
                            "ip": -2018147075,
                            "port": 6302
                          }
                        ],
                        "version": 0,
                        "reinit_date": 0,
                        "priority": 0,
                        "expire_at": 0
                      },
                      "version": -1,
                      "signature": "nUGB77UAkd2+ZAL5PgInb3TvtuLLXJEJ2icjAUKLv4qIGB3c/O9k/v0NKwSzhsMP0ljeTGbcIoMDw24qf3goCg=="
                    }
                  ]
                }
              },
              "liteservers": [
                {
                  "ip": -2018135749,
                  "port": 53312,
                  "id": {
                    "@type": "pub.ed25519",
                    "key": "aF91CuUHuuOv9rm2W5+O/4h38M3sRm40DtSdRxQhmtQ="
                  }
                },
                {
                  "ip": -1136338705,
                  "port": 19925,
                  "id": {
                    "@type": "pub.ed25519",
                    "key": "ucho5bEkufbKN1JR1BGHpkObq602whJn3Q3UwhtgSo4="
                  }
                }
              ],
              "validator": {
                "@type": "validator.config.global",
                "zero_state": {
                  "file_hash": "XplPz01CXAps5qeSWUtxcyBfdAo5zVb1N979KLSKD24=",
                  "seqno": 0,
                  "root_hash": "F6OpKZKqvqeFp6CQmFomXNMfMj2EnaUSOXN+Mh+wVWk=",
                  "workchain": -1,
                  "shard": -9223372036854775808
                },
                "init_block": {
                  "file_hash": "Yqmli3gIUgt3KjeU4n2d1ZmcJ3R4zJBMYWhM+tZF4V8=",
                  "seqno": 18155329,
                  "root_hash": "iyOany4cPE2u6h/Um7OAmHDQ+Nba8Am+g/qZJ5M4P9M=",
                  "workchain": -1,
                  "shard": -9223372036854775808
                },
                "hardforks": [
                  {
                    "file_hash": "t/9VBPODF7Zdh4nsnA49dprO69nQNMqYL+zk5bCjV/8=",
                    "seqno": 8536841,
                    "root_hash": "08Kpc9XxrMKC6BF/FeNHPS3MEL1/Vi/fQU/C9ELUrkc=",
                    "workchain": -1,
                    "shard": -9223372036854775808
                  }
                ]
              },
              "timestamp": 1663091581
            }""";
}
