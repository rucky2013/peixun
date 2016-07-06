package com.ync365.px.model.bo;

public class FileUploadBo {
    
        private Long id ;
        
        private String url;
        
        private String deleteUrl;
        
        private String deleteType;
        
        private String name;
        
        private String type;
        
        private String size;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDeleteUrl() {
            return deleteUrl;
        }

        public void setDeleteUrl(String deleteUrl) {
            this.deleteUrl = deleteUrl;
        }

        public String getDeleteType() {
            return deleteType;
        }

        public void setDeleteType(String deleteType) {
            this.deleteType = deleteType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
        
        
}
