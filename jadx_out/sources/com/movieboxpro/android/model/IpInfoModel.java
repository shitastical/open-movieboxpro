package com.movieboxpro.android.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class IpInfoModel {
    private String ip;
    private LocationBean location;

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocationBean getLocation() {
        return this.location;
    }

    public void setLocation(LocationBean location) {
        this.location = location;
    }

    public static class NamesBean {

        /* JADX INFO: renamed from: de, reason: collision with root package name */
        private String f14de;
        private String en;
        private String es;
        private String fr;
        private String ja;

        @SerializedName("pt-BR")
        private String ptBR;
        private String ru;

        @SerializedName("zh-CN")
        private String zhCN;

        public String getDe() {
            return this.f14de;
        }

        public void setDe(String de2) {
            this.f14de = de2;
        }

        public String getEn() {
            return this.en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getEs() {
            return this.es;
        }

        public void setEs(String es) {
            this.es = es;
        }

        public String getFr() {
            return this.fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        public String getJa() {
            return this.ja;
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        public String getPtBR() {
            return this.ptBR;
        }

        public void setPtBR(String ptBR) {
            this.ptBR = ptBR;
        }

        public String getRu() {
            return this.ru;
        }

        public void setRu(String ru) {
            this.ru = ru;
        }

        public String getZhCN() {
            return this.zhCN;
        }

        public void setZhCN(String zhCN) {
            this.zhCN = zhCN;
        }
    }

    public static class LocationBean {
        private ContinentBean continent;
        private CountryBean country;
        private RegisteredCountryBean registered_country;
        private List<SubdivisionsBean> subdivisions;

        public List<SubdivisionsBean> getSubdivisions() {
            return this.subdivisions;
        }

        public void setSubdivisions(List<SubdivisionsBean> subdivisions) {
            this.subdivisions = subdivisions;
        }

        public ContinentBean getContinent() {
            return this.continent;
        }

        public void setContinent(ContinentBean continent) {
            this.continent = continent;
        }

        public CountryBean getCountry() {
            return this.country;
        }

        public void setCountry(CountryBean country) {
            this.country = country;
        }

        public RegisteredCountryBean getRegistered_country() {
            return this.registered_country;
        }

        public void setRegistered_country(RegisteredCountryBean registered_country) {
            this.registered_country = registered_country;
        }

        public static class ContinentBean {
            private String code;
            private int geoname_id;
            private NamesBean names;

            public String getCode() {
                return this.code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int geoname_id) {
                this.geoname_id = geoname_id;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean names) {
                this.names = names;
            }
        }

        public static class CountryBean {
            private int geoname_id;
            private String iso_code;
            private NamesBean names;

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int geoname_id) {
                this.geoname_id = geoname_id;
            }

            public String getIso_code() {
                return this.iso_code;
            }

            public void setIso_code(String iso_code) {
                this.iso_code = iso_code;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean names) {
                this.names = names;
            }
        }

        public static class RegisteredCountryBean {
            private int geoname_id;
            private String iso_code;
            private NamesBean names;

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int geoname_id) {
                this.geoname_id = geoname_id;
            }

            public String getIso_code() {
                return this.iso_code;
            }

            public void setIso_code(String iso_code) {
                this.iso_code = iso_code;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean names) {
                this.names = names;
            }
        }

        public static class SubdivisionsBean {
            private int geoname_id;
            private String iso_code;
            private NamesBean names;

            public int getGeoname_id() {
                return this.geoname_id;
            }

            public void setGeoname_id(int geoname_id) {
                this.geoname_id = geoname_id;
            }

            public String getIso_code() {
                return this.iso_code;
            }

            public void setIso_code(String iso_code) {
                this.iso_code = iso_code;
            }

            public NamesBean getNames() {
                return this.names;
            }

            public void setNames(NamesBean names) {
                this.names = names;
            }
        }
    }
}
