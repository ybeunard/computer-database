package com.cdb.dto;

import java.util.List;

public class PageDto {
    
    private final List<OrdinateurDto> contenue;
    
    private final int pageSuiv;
    
    private final int pagePrec;
    
    private final List<Integer> pagination;
    
    private final int numPage;
    
    private final int nbParPage;
    
    private PageDto(PageDtoBuilder builder) {
        
        this.contenue = builder.contenue;
        
        this.pageSuiv = builder.pageSuiv;
        
        this.pagePrec = builder.pagePrec;
        
        this.pagination = builder.pagination;
        
        this.numPage = builder.numPage;
        
        this.nbParPage = builder.nbParPage;
        
    }

    public List<OrdinateurDto> getContenue() {
        return contenue;
    }

    public int getPageSuiv() {
        return pageSuiv;
    }

    public int getPagePrec() {
        return pagePrec;
    }

    public List<Integer> getPagination() {
        return pagination;
    }

    public int getNumPage() {
        return numPage;
    }

    public int getNbParPage() {
        return nbParPage;
    }
    
    public static class PageDtoBuilder {
        
        private List<OrdinateurDto> contenue;
        
        private int pageSuiv;
        
        private int pagePrec;
        
        private List<Integer> pagination;
        
        private int numPage;
        
        private int nbParPage;
        
        public PageDtoBuilder contenue(List<OrdinateurDto> contenue) {
            
            this.contenue = contenue;
            return this;
            
        }
        
        public PageDtoBuilder pageSuiv(int pageSuiv) {
            
            this.pageSuiv = pageSuiv;
            return this;
            
        }
        
        public PageDtoBuilder pagePrec(int pagePrec) {
            
            this.pagePrec = pagePrec;
            return this;
            
        }
        
            public PageDtoBuilder pagination(List<Integer> pagination) {
            
            this.pagination = pagination;
            return this;
            
        }

        public PageDtoBuilder numPage(int numPage) {
            
            this.numPage = numPage;
            return this;
            
        }

        public PageDtoBuilder nbParPage(int nbParPage) {
            
            this.nbParPage = nbParPage;
            return this;
            
        }
        
        public PageDto build() {
            
            return new PageDto(this);
            
        }
        
    }

}
