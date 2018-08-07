# -*- coding:UTF-8 -*-
import scrapy

class ComicSpider(scrapy.Spider):

    name = "comic"
    allowed_domains = ['comic.kukudm.com']
    start_urls = ['http://comic.kukudm.com/comiclist/3/']

    def parse(self, response):
        link_urls = response.xpath('//dd/a[1]/@href').extract()
        for each_link in link_urls:
            print('http://comic.kukudm.com' + each_link)